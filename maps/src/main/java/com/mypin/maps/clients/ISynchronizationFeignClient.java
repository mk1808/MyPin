package com.mypin.maps.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("synchronization")
public interface ISynchronizationFeignClient {

}
