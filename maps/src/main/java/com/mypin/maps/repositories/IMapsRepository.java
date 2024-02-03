package com.mypin.maps.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypin.maps.models.Map;

@Repository
public interface IMapsRepository extends JpaRepository<Map, UUID>{

}
