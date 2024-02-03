package com.mypin.pinLists.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("maps")
public interface IMapsFeignClient {

}
