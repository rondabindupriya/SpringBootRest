package com.springboot.restapi.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.springboot.restapi"})
public class SpringBootRestAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestAPIApplication.class, args);
	}

}
