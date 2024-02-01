package com.mypin.pinLists;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PinListsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinListsApplication.class, args);
	}

}
