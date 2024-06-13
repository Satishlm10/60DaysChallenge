package com._daysLearningChallenge.BestStore.Repositories;

import com._daysLearningChallenge.BestStore.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product,Integer> {
}
