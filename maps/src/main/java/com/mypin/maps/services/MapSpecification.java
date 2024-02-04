package com.mypin.maps.services;

import org.springframework.data.jpa.domain.Specification;

import com.mypin.maps.enums.MapSort;
import com.mypin.maps.models.Map;

import jakarta.persistence.criteria.Predicate;

public class MapSpecification {

	public static Specification<Map> filter(String title, MapSort sort, Boolean isMyOwn, Boolean isSharedWithMe) {
		return null;
		/*
		 return (root, query, criteriaBuilder) -> {
	            Predicate brandPredicate = 
	criteriaBuilder.like(root.get("phoneBrand"),StringUtils.isBlank(phoneBrand) 
	? likePattern("") : phoneBrand);
	            Predicate namePredicate = 
	criteriaBuilder.like(root.get("phoneName"), StringUtils.isBlank(phoneName) 
	? likePattern("") : phoneName);
	            return criteriaBuilder.and(namePredicate, brandPredicate);
	        };*/
	    }

	    private static String likePattern(String value) {
	        return "%" + value + "%";
	    }

}
