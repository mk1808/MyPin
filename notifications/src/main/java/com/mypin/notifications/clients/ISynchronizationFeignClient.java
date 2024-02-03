package com.mypin.notifications.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("synchronization")
public interface ISynchronizationFeignClient {

}
