package com.loda.criteriaapi;

import com.loda.criteriaapi.repository.CustomUserRepository;
import com.loda.criteriaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loda.criteriaapi.entity.User.UserType;

@SpringBootApplication
@RequiredArgsConstructor
public class CriteriaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriteriaApiApplication.class, args);
	}
	private final UserRepository userRepository;
	private final CustomUserRepository customUserRepository;

	@Bean
	CommandLineRunner run() {
		return args -> {
			// Get User by ID
			System.out.println(customUserRepository.getUserById(10L));
			System.out.println("=======");

			// Get User by Name Like and Type
			System.out.println(customUserRepository.getUserByComplexConditions("name-%", UserType.NORMAL));
			System.out.println("=======");
		};
	}

}
