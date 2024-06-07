package com._daysLearningChallenge.SpringDataJpa_EntityMapping_Practice.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_no")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name="product_price")
    private String price;

    @ManyToMany(mappedBy = "product")
    private List<Orders> order;
}

