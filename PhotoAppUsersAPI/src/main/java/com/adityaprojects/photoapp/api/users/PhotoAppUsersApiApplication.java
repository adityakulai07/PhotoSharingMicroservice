package com.adityaprojects.photoapp.api.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppUsersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppUsersApiApplication.class, args);
	}

}
