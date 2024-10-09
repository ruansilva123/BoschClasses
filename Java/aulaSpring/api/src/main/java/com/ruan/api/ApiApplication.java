package com.ruan.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@RestController
	@RequestMapping("/api")
	public static class ApiController {

		@GetMapping("/hello")
		public String hello() {
			return "Hello, Spring Boot!";
		}
	}
}
