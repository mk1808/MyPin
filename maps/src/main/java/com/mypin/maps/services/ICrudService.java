package com.mypin.maps.services;

import java.util.UUID;

public interface ICrudService<T> {
	T save(T obj);

	T update(T obj, UUID id);

	T get(UUID id);

	void delete(UUID id);

}
