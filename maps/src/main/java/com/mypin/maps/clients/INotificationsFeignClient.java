package com.mypin.maps.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("notifications")
public interface INotificationsFeignClient {

}
