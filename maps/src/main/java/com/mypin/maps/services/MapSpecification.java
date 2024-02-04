package com.mypin.maps.services;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.mypin.maps.enums.MapSort;
import com.mypin.maps.models.Map;
import com.mypin.maps.models.Sharing;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class MapSpecification {

	public static Specification<Map> filter(String title, MapSort sort, Boolean isMyOwn, Boolean isSharedWithMe,
			UUID ownerId) {

		return (root, query, criteriaBuilder) -> {
			Predicate titlePredicate = criteriaBuilder.like(root.get("title"),
					title != null && !title.isBlank() ? likePattern(title) : likePattern(""));

			Predicate isMyOwnPredicate = isMyOwn == false ? criteriaBuilder.conjunction()
					: criteriaBuilder.equal(root.get("ownerId"), ownerId);

			Predicate isSharedWithMePredicate = isSharedWithMe == false ? criteriaBuilder.conjunction()
					: getSharedWithMePredicate(root, query, criteriaBuilder, ownerId);

			return criteriaBuilder.and(titlePredicate, isMyOwnPredicate, isSharedWithMePredicate);
		};
	}

	private static Predicate getSharedWithMePredicate(Root<Map> mapRoot, CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder, UUID userId) {
		Subquery<Sharing> subquery = query.subquery(Sharing.class);
		Root<Sharing> sharingRoot = subquery.from(Sharing.class);

		Predicate mapIdPredicate = criteriaBuilder.equal(sharingRoot.get("mapId"), mapRoot.get("id"));
		Predicate userIdPredicate = criteriaBuilder.equal(sharingRoot.get("userId"), userId);
		subquery.select(sharingRoot).where(mapIdPredicate, userIdPredicate);
		return criteriaBuilder.exists(subquery);
	}

	private static String likePattern(String value) {
		return "%" + value + "%";
	}

}
