package com._daysLearningChallenge.SpringSecurityBasics.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Join;
@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    private long id;


    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

// table for enforcing role based authorization