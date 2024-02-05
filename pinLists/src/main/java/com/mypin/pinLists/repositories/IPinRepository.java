package com.mypin.pinLists.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypin.pinLists.models.Pin;

@Repository
public interface IPinRepository extends JpaRepository<Pin, UUID> {
	
	List<Pin> findByPinListIdOrderByOrderNoAsc(UUID pinListId);
}
