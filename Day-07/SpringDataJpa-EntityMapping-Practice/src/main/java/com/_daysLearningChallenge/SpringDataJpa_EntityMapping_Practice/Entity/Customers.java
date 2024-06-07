package com._daysLearningChallenge.SpringDataJpa_EntityMapping_Practice.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @Column(name="customer_name")
    private String name;

    @Column(name = "customer_email")
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> orders;
}
