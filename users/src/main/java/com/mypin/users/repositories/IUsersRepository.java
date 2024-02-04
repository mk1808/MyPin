package com.mypin.users.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypin.users.models.AppUser;

@Repository
public interface IUsersRepository extends JpaRepository<AppUser, UUID> {

	List<AppUser> findByLoginOrEmail(String login, String email);

	Optional<AppUser> findByEmail(String email);
	
}
