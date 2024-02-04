package com.mypin.maps.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypin.maps.models.Sharing;

@Repository
public interface ISharingRepository extends JpaRepository<Sharing, UUID> {
	List<Sharing> getByMapId(UUID mapId);
}
