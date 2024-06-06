package com._daysLearningChallenge.SpringDataJpa_Practice.Services.ServiceImpl;

import com._daysLearningChallenge.SpringDataJpa_Practice.Entities.Employee;
import com._daysLearningChallenge.SpringDataJpa_Practice.Repositories.EmployeeRepo;

import com._daysLearningChallenge.SpringDataJpa_Practice.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    private Scanner input = new Scanner(System.in);
    @Override
    public void insertEmployee()
    {

        System.out.println("Enter a name to insert into database:");
        String name = input.nextLine().trim();

        System.out.println("Enter role of Employee:");
        String role = input.nextLine().trim();

        Employee employee = new Employee();

        employee.setName(name);
        employee.setRole(role);

        Employee savedEmployee = employeeRepo.save(employee);

    }

    @Override
    public void updateEmployee() {
        try {
            System.out.println("Enter the ID of the employee to update:");
            Long id = Long.parseLong(input.nextLine().trim());

            Optional<Employee> optionalEmployee = employeeRepo.findById(id);
            if (optionalEmployee.isPresent())
            {
                Employee employee = optionalEmployee.get();

                System.out.println("Current Name: " + employee.getName());
                System.out.println("Enter a new name to update:");
                String newName = input.nextLine().trim();

                if (newName.isEmpty()) {
                    System.out.println("Name cannot be empty. Please enter a valid name.");
                    return;
                }

                employee.setName(newName);
                Employee updatedEmployee = employeeRepo.save(employee);
                System.out.println("Employee updated with ID: " + updatedEmployee.getId());
            }
            else
            {
                System.out.println("Employee with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid numerical ID.");
        } catch (Exception e) {
            System.out.println("An error occurred while updating the employee: " + e.getMessage());
        }
    }

    @Override
    public void deleteEmployee()
    {
        try {
            System.out.println("Enter the ID of the employee to delete:");
            Long id = Long.parseLong(input.nextLine().trim());

            Optional<Employee> optionalEmployee = employeeRepo.findById(id);
            if (optionalEmployee.isPresent()) {
                employeeRepo.deleteById(id);
                System.out.println("Employee with ID " + id + " has been deleted.");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid numerical ID.");
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the employee: " + e.getMessage());
        }
    }
}



