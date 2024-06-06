package com._daysLearningChallenge.SpringDataJpa_Practice.Repositories;

import com._daysLearningChallenge.SpringDataJpa_Practice.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    // inheriting the methods in JpaRepository it provides easy to use crud operations without need of writing boilerplate code
}
