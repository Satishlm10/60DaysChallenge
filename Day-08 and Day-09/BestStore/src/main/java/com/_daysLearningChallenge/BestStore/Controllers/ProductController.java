package com._daysLearningChallenge.BestStore.Controllers;

import com._daysLearningChallenge.BestStore.Entities.Dto.ProductDto;
import com._daysLearningChallenge.BestStore.Entities.Product;
import com._daysLearningChallenge.BestStore.Services.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    // Products Page - Read
    @GetMapping(value = {"","/"})
    public String showProductList(Model model)
    {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products",products);
        return "products/index";
    }

    // Create new products
    @GetMapping("/create")
    public String displayCreatePage(Model model)
    {
        model.addAttribute("productDto",new ProductDto());
        return "products/CreateProduct";
    }

    // Submit the newly created product - Create
    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("productDto") ProductDto productDto, BindingResult result)
    {

        if(productDto.getImageFilename().isEmpty())
        {
            result.addError(new FieldError("productDto","imageFilename","The image file is required"));
        }
        if(result.hasErrors()){
            return "products/CreateProduct";
        }

        productService.saveFormData(productDto);

        return "redirect:/products";
    }

    // Update existing Product - Update
    @GetMapping("/edit")
    public String displayEditPage(Model model, @RequestParam int id)
    {   try
        {
            Product product = productService.findProductById(id);
            model.addAttribute("product",product);
            ProductDto productDto = this.modelMapper.map(product,ProductDto.class);
            model.addAttribute("productDto",productDto);

        }
        catch (Exception ex)
        {
            System.out.println("Exception "+ ex.getMessage());
            return "redirect:/products";
        }

        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProducts(Model model, @RequestParam int id, @Valid @ModelAttribute ProductDto productDto, BindingResult result)
    {
        try
        {
            Product product = productService.updateProducts(productDto,id);
            model.addAttribute("product",product);

            if(result.hasErrors()){
                return "redirect:/EditProduct";
            }

        }
        catch (Exception ex)
        {
            System.out.println("Exception "+ ex.getMessage());
            return "redirect:/products";
        }

     return "redirect:/products";
    }

    // Deleting Products
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
