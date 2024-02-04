package com.mypin.maps.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mypin.maps.dtos.SharingDto;
import com.mypin.maps.enums.MapSort;
import com.mypin.maps.models.Map;
import com.mypin.maps.models.Sharing;

import jakarta.validation.Valid;

@Service
public interface IMapsService extends ICrudService<Map>{
	
	public Map patchName(String name, UUID id);
	
	public Map patchUpdated(UUID id);

	public Sharing share(UUID id, @Valid SharingDto sharingDto);

	public void notify(UUID id);

	public List<Map> search(String title, MapSort sort, Boolean isSharedWithMe, Boolean isMyOwn);
	
	

}
