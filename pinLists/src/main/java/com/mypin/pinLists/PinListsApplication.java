package com.mypin.pinLists;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PinListsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinListsApplication.class, args);
	}

}
