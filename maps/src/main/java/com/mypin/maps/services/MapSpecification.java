package com.mypin.maps.services;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.mypin.maps.enums.MapSort;
import com.mypin.maps.models.Map;

import jakarta.persistence.criteria.Predicate;

public class MapSpecification {

	public static Specification<Map> filter(String title, MapSort sort, Boolean isMyOwn, Boolean isSharedWithMe, UUID ownerId) {

		return (root, query, criteriaBuilder) -> {
			Predicate titlePredicate = criteriaBuilder.like(root.get("title"),
					title != null && !title.isBlank() ? likePattern("") : title);
			
			Predicate isMyOwnPredicate = isMyOwn == null ? criteriaBuilder.conjunction()
					: criteriaBuilder.equal(root.get("ownerId"), ownerId);
			
			Predicate isSharedWithMePredicate = isSharedWithMe == null ? criteriaBuilder.conjunction()
					: criteriaBuilder.equal(root.get("isSharedWithMe"), isSharedWithMe);

			return criteriaBuilder.and(titlePredicate, isMyOwnPredicate, isSharedWithMePredicate);
		};
	}

	private static String likePattern(String value) {
		return "%" + value + "%";
	}
	

}
