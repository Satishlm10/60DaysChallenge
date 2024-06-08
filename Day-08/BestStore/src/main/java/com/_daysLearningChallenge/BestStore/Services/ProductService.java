package com._daysLearningChallenge.BestStore.Services;


import com._daysLearningChallenge.BestStore.Entities.Product;
import com._daysLearningChallenge.BestStore.Repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;

   public List<Product> findAllProducts(){
       return productsRepository.findAll();
   }
}
