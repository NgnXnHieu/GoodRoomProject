package com.example.backendTeam12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BackendTeam12Application {

	public static void main(String[] args) {
		SpringApplication.run(BackendTeam12Application.class, args);
	}

}
