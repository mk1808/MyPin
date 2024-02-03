package com.mypin.pinLists.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("synchronization")
public interface ISynchronizationFeignClient {

}
