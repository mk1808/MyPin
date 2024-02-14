package com.mypin.maps.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mypin.maps.clients.INotificationsFeignClient;
import com.mypin.maps.clients.ISynchronizationFeignClient;
import com.mypin.maps.clients.IUsersFeignClient;
import com.mypin.maps.dtos.AppUserDto;
import com.mypin.maps.dtos.NotificationDto;
import com.mypin.maps.dtos.SharingDto;
import com.mypin.maps.dtos.SynchronizationDto;
import com.mypin.maps.enums.MapSort;
import com.mypin.maps.enums.NotificationType;
import com.mypin.maps.exceptions.ForbiddenException;
import com.mypin.maps.exceptions.ResourceNotFoundException;
import com.mypin.maps.models.Map;
import com.mypin.maps.models.Sharing;
import com.mypin.maps.repositories.IMapsRepository;
import com.mypin.maps.repositories.ISharingRepository;

import jakarta.validation.Valid;

@Service
public class MapsService implements IMapsService {

	private final IMapsRepository repository;
	private final ISharingRepository sharingRepository;
	private final IUsersFeignClient usersFeignClient;
	private final INotificationsFeignClient notificationsFeignClient;
	private final ISynchronizationFeignClient synchronizationFeignClient;

	public MapsService(IMapsRepository repository, ISharingRepository sharingRepository,
			IUsersFeignClient usersFeignClient, INotificationsFeignClient notificationsFeignClient,
			ISynchronizationFeignClient synchronizationFeignClient) {
		super();
		this.repository = repository;
		this.sharingRepository = sharingRepository;
		this.usersFeignClient = usersFeignClient;
		this.notificationsFeignClient = notificationsFeignClient;
		this.synchronizationFeignClient = synchronizationFeignClient;
	}

	@Override
	public Map save(Map map) {
		map.setCreatedDate(new Date());
		map.setUpdatedDate(new Date());
		map.setOwnerId(getCurrentUser());
		return repository.save(map);
	}

	@Override
	public Map update(Map updated, UUID id) {

		Optional<Map> map = repository.findById(id);
		if (map.isEmpty()) {
			throw new ResourceNotFoundException("Map not found");
		}
		Map existingMap = map.get();
		if (!hasPermissions(existingMap)) {
			throw new ForbiddenException("User does not have rights to map");
		}
		existingMap.setTitle(updated.getTitle());
		existingMap.setUpdatedDate(new Date());
		return repository.save(existingMap);
	}

	@Override
	public Map get(UUID id) {
		Optional<Map> map = repository.findById(id);
		if (map.isEmpty()) {
			throw new ResourceNotFoundException("Map not found");
		}
		return map.get();
	}

	@Override
	public void delete(UUID id) {
		Optional<Map> map = repository.findById(id);
		if (map.isEmpty()) {
			throw new ResourceNotFoundException("Map not found");
		}
		if (!hasFullPermissions(map.get())) {
			throw new ForbiddenException("User does not have rights to map");
		}
		repository.deleteById(id);
	}

	@Override
	public Map patchTitle(String title, UUID id) {
		Optional<Map> map = repository.findById(id);
		if (map.isEmpty()) {
			throw new ResourceNotFoundException("Map not found");
		}
		Map existingMap = map.get();
		if (!hasPermissions(existingMap)) {
			throw new ForbiddenException("User does not have rights to map");
		}

		existingMap.setTitle(title);
		existingMap.setUpdatedDate(new Date());
		synchronizeMap(existingMap);
		return repository.save(existingMap);
	}

	@Override
	public Map patchUpdated(UUID id) {
		Optional<Map> map = repository.findById(id);
		if (map.isEmpty()) {
			throw new ResourceNotFoundException("Map not found");
		}
		Map existingMap = map.get();
		if (!hasPermissions(existingMap)) {
			throw new ForbiddenException("User does not have rights to map");
		}
		existingMap.setUpdatedDate(new Date());
		synchronizeMap(existingMap);
		return repository.save(existingMap);
	}

	@Override
	public Sharing share(UUID id, @Valid SharingDto sharingDto) {
		Optional<Map> map = repository.findById(id);
		if (map.isEmpty()) {
			throw new ResourceNotFoundException("Map not found");
		}

		AppUserDto user = usersFeignClient.getByEmail(sharingDto.email).getBody();
		if (user == null) {
			throw new ResourceNotFoundException("User with email not found");
		}

		Sharing sharing = new Sharing();
		sharing.setMapId(id);
		sharing.setUserId(user.id);
		sharingRepository.save(sharing);
		sendSharingNotification(sharing);
		return sharing;
	}

	@Override
	public void notify(UUID id) {
		Map map = get(id);
		List<Sharing> sharingList = sharingRepository.getByMapId(id);
		if (!sharingList.isEmpty()) {
			final UUID currentUserId = getCurrentUser();
			List<NotificationDto> notifications = new ArrayList<>();
			notifications.addAll(sharingList.stream().map(fillCallToMapNotification(currentUserId)).toList());
			notifications.add(fillCallToMapNotification(currentUserId, map.getOwnerId()));
			notifications.stream().filter(filterNotCurrentUser(currentUserId)).forEach(sendNotification());
		}
	}

	private Predicate<? super NotificationDto> filterNotCurrentUser(final UUID currentUserId) {
		return notification -> !notification.ownerId.equals(currentUserId);
	}

	private Consumer<? super NotificationDto> sendNotification() {
		return notification -> notificationsFeignClient.create(notification);
	}

	private Function<? super Sharing, NotificationDto> fillCallToMapNotification(UUID currentUserId) {
		return sharing -> fillCallToMapNotification(sharing.getUserId(), currentUserId);
	}

	private NotificationDto fillCallToMapNotification(UUID callingUserId, UUID targetUserId) {
		return new NotificationDto(targetUserId, callingUserId, NotificationType.CALL_TO_MAP, "call");
	}

	@Override
	public List<Map> search(String title, MapSort sort, Boolean isSharedWithMe, Boolean isMyOwn) {
		UUID user = getCurrentUser();
		Specification<Map> specification = MapSpecification.filter(title, sort, isMyOwn, isSharedWithMe, user);
		return repository.findAll(specification);
	}

	private void synchronizeMap(Map map) {
		SynchronizationDto synchronizationDto = new SynchronizationDto();
		synchronizationDto.channel = map.getId().toString();
		synchronizationDto.content = ""; // TODO fill map
		synchronizationFeignClient.sendSynchronizationMessage(synchronizationDto);
	}

	private void sendSharingNotification(Sharing sharing) {
		NotificationDto notification = new NotificationDto(sharing.getUserId(), sharing.getUserId(),
				NotificationType.MAP_SHARED_TO_USER, "MAP_SHARED_TO_USER"); // TODO: fill content
		notificationsFeignClient.create(notification);
	}

	private UUID getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		String uuid = context.getAuthentication().getName();

		System.out.println("name: " + uuid);
		return UUID.fromString(uuid);
	}

	private boolean hasFullPermissions(Map map) {
		UUID user = getCurrentUser();
		return map.getOwnerId().equals(user);
	}

	private boolean hasPermissions(Map map) {
		UUID user = getCurrentUser();
		List<Sharing> sharingList = sharingRepository.getByMapId(map.getId());
		List<UUID> usersWithPermissions = sharingList.stream().map(sharing -> sharing.getUserId()).toList();
		return usersWithPermissions.contains(user);
	}

}
