package com._daysLearningChallenge.BestStore.Controllers;

import com._daysLearningChallenge.BestStore.Entities.Dto.ProductDto;
import com._daysLearningChallenge.BestStore.Entities.Product;
import com._daysLearningChallenge.BestStore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping(value = {"","/"})
    public String showProductList(Model model){
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products",products);
        return "products/index";
    }

    @GetMapping("/create")
    public String displayCreatePage(Model model){
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto",productDto);
        return "products/CreateProduct";
    }
}
