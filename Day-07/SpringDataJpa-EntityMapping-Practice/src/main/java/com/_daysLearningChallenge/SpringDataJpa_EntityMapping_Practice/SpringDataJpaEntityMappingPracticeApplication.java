package com._daysLearningChallenge.SpringDataJpa_EntityMapping_Practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaEntityMappingPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaEntityMappingPracticeApplication.class, args);
	}
	// Entity Mapping Practice
	/*
		one to many relationship between customer and orders
		many to many relationship between orders and products
	 */
}
