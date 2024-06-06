package com._daysLearningChallenge.SpringDataJpa_Practice.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity     // Annotation for ORM (Java code gets converted to a able with following fields as columns of the table)
@Table(name = "employees")
public class Employee {
    @Id // Annotation to make the id field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation to achieve AutoIncrement of column id
    private Long id;

    @Column(name = "employee_name") // Annotation for columns of the employee table
    private String name;

    @Column(name = "employee_role")
    private String role;


}
