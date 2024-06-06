package com._daysLearningChallenge.SpringDataJpa_Practice.Services;


import com._daysLearningChallenge.SpringDataJpa_Practice.Entities.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    public void insertEmployee();

    public void updateEmployee();

    public void deleteEmployee();

}
