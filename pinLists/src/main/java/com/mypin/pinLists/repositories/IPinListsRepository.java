package com.mypin.pinLists.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypin.pinLists.models.PinList;

@Repository
public interface IPinListsRepository extends JpaRepository<PinList, UUID> {

	List<PinList> findByMapId(UUID mapId);
}
