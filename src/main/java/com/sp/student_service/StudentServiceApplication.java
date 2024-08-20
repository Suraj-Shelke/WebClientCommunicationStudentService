package com.sp.student_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}
	@Value("${collegeService.base.url}")
	private String collegeBaseUrl;
	@Bean
	public WebClient webClient()
	{
		return WebClient.builder().baseUrl(collegeBaseUrl).build();
	}

}
