package com.mypin.notifications.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypin.notifications.models.Notification;

@Repository
public interface INotificationsRepository extends JpaRepository<Notification, UUID> {

}
