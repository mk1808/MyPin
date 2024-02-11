package com.mypin.maps.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.data.jpa.domain.Specification;
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
			IUsersFeignClient usersFeignClient, INotificationsFeignClient notificationsFeignClient, ISynchronizationFeignClient synchronizationFeignClient) {
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
		// TODO: save user id
		return repository.save(map);
	}

	@Override
	public Map update(Map updated, UUID id) {
		// TODO: check if right user
		Optional<Map> map = repository.findById(id);
		if (map.isEmpty()) {
			throw new ResourceNotFoundException("Map not found");
		}
		Map existingMap = map.get();
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
		// TODO: check if right user
		Optional<Map> map = repository.findById(id);
		if (map.isEmpty()) {
			throw new ResourceNotFoundException("Map not found");
		}
		repository.deleteById(id);
	}

	@Override
	public Map patchTitle(String title, UUID id) {
		// TODO: check if right user
		Optional<Map> map = repository.findById(id);
		if (map.isEmpty()) {
			throw new ResourceNotFoundException("Map not found");
		}
		Map existingMap = map.get();
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
		/*AppUserDto user = usersFeignClient.getByEmail(sharingDto.email).getBody();
		if (user == null) {
			throw new ResourceNotFoundException("User with email not found");
		}*/
		
		Sharing sharing = new Sharing();
		sharing.setMapId(id);
		sharing.setUserId(UUID.fromString("accd2a12-8d3b-438e-8a73-d12e3419030b"));
		sharingRepository.save(sharing);
		sendSharingNotification(sharing);
		return sharing;
	}

	@Override
	public void notify(UUID id) {
		List<Sharing> sharingList = sharingRepository.getByMapId(id);
		if (!sharingList.isEmpty()) {
			// TODO: get owner
			final UUID ownerId = UUID.randomUUID();
			List<NotificationDto> notifications = sharingList.stream().map(fillNotification(ownerId)).toList();
			notifications.add(new NotificationDto(ownerId, ownerId, NotificationType.CALL_TO_MAP, "call"));
			notifications.forEach(notification -> notificationsFeignClient.create(notification));

		}
	}

	private Function<? super Sharing, NotificationDto> fillNotification(UUID ownerId) {
		return sharing -> new NotificationDto(ownerId, sharing.getUserId(), NotificationType.CALL_TO_MAP, "call");
	}

	@Override
	public List<Map> search(String title, MapSort sort, Boolean isSharedWithMe, Boolean isMyOwn) {
		//TODO: get user id
		Specification<Map> specification = MapSpecification.filter(title, sort, isMyOwn, isSharedWithMe, UUID.fromString("accd2a12-8d3b-438e-8a73-d12e3419030b"));
		return repository.findAll(specification);

	}
	
	private void synchronizeMap(Map map) {
		SynchronizationDto synchronizationDto = new SynchronizationDto();
		synchronizationDto.channel = map.getId().toString();
		synchronizationDto.content = ""; // TODO fill map
		synchronizationFeignClient.sendSynchronizationMessage(synchronizationDto);
	}
	
	private void sendSharingNotification(Sharing sharing) {
		NotificationDto notification = new NotificationDto(sharing.getUserId(), sharing.getUserId(), NotificationType.MAP_SHARED_TO_USER, "MAP_SHARED_TO_USER"); //TODO: fill content
		notificationsFeignClient.create(notification);
	}

}
