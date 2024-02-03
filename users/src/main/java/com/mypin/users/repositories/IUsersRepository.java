package com.mypin.users.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypin.users.models.AppUser;

@Repository
public interface IUsersRepository extends JpaRepository<AppUser, UUID> {

}
