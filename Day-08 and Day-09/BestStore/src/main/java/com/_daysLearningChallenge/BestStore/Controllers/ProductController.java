package com._daysLearningChallenge.BestStore.Controllers;

import com._daysLearningChallenge.BestStore.Entities.Dto.ProductDto;
import com._daysLearningChallenge.BestStore.Entities.Product;
import com._daysLearningChallenge.BestStore.Services.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

// Products Page - Read all items
    @GetMapping(value = {""})
    public String showProductList( Model model)
    {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products",products);
        return "products/index";
    }

    // show all products with sorting feature
    @GetMapping("/{field}")
    public String showProductListWithSorting(@PathVariable String field, Model model)
    {
        List<Product> products = productService.findAllProductsWithSorting(field);
        model.addAttribute("products",products);
        return "products/index";
    }

    // show products with pagination based on offset and pageSize
    @GetMapping("/pagination/{offset}/{pageSize}")
    public String showProductListWithPagination(@PathVariable int offset,@PathVariable int pageSize, Model model)
    {
        Page<Product> products = productService.findAllProductsWithPagination(offset,pageSize);
        model.addAttribute("products",products);
        return "products/index";
    }

    // show products based on pagination with sorting feature
    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public String showProductListWithSortingAndPagination(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field, Model model)
    {
        Page<Product> products = productService.findAllProductsWithSortingAndPagination(offset,pageSize,field);
        model.addAttribute("products",products);
        return "products/index";
    }

    // Create new products - displaying create new product page/form
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

    // Update existing Product - displaying Update page/form
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

    // updating the product with the given id
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
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    // Get a single item
    /* @GetMapping("/{id}")
    public String showSingleProduct(Model model,@PathVariable int id){
        Product product = productService.findProductById(id);
        model.addAttribute("products",product);
        return "products/index";
    }
    */
}
