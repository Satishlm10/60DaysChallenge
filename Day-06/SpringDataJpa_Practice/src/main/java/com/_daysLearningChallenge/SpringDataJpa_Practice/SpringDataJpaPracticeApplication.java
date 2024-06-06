package com._daysLearningChallenge.SpringDataJpa_Practice;

import com._daysLearningChallenge.SpringDataJpa_Practice.Services.ServiceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataJpaPracticeApplication implements CommandLineRunner {


	@Autowired
	private EmployeeServiceImpl employeeService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner input = new Scanner(System.in);

		System.out.println("Spring Data JPA Practice");
		System.out.println();

		System.out.println("Perform Create, Update, Delete database operation: ");
		System.out.println("Select from following options to perform database operations.");
		System.out.println("1. Create");
		System.out.println("2. Update");
		System.out.println("3. Delete");

		System.out.print("Enter your choice: ");
		int choice = input.nextInt();
		input.nextLine(); // Consume newline

		switch (choice) {
			case 1:
				employeeService.insertEmployee();
				break;

			case 2:
				employeeService.updateEmployee();
				break;

			case 3:
				employeeService.deleteEmployee();
				break;

			default:
				System.out.println("Invalid choice. Please select 1, 2, or 3.");
				break;
		}
	}



}
