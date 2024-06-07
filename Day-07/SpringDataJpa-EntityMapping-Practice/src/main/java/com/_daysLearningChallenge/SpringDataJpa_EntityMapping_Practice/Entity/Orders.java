package com._daysLearningChallenge.SpringDataJpa_EntityMapping_Practice.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_no")
    private Long id;

    @Column(name = "order_date")
    private Date date;

    @Column(name="payment_type")
    private String payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToMany
    @JoinTable(name = "order_product_id",joinColumns = @JoinColumn(name = "order_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Products> product;

}
