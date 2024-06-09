package com._daysLearningChallenge.BestStore.Services;


import com._daysLearningChallenge.BestStore.Entities.Dto.ProductDto;
import com._daysLearningChallenge.BestStore.Entities.Product;
import com._daysLearningChallenge.BestStore.Repositories.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ModelMapper modelMapper;

   public List<Product> findAllProducts(){
       return productsRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
   }

   public void saveFormData(ProductDto productDto){

       MultipartFile image = productDto.getImageFilename();
       Date createdAt = new Date();
       String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

       try {
           // Set the upload directory to src/main/resources/static/images
           String uploadDir = "BestStore/src/main/resources/static/images/";
           Path uploadPath = Paths.get(uploadDir);
           Path filePath = uploadPath.resolve(storageFileName);
           if (!Files.exists(uploadPath)) {
               Files.createDirectories(uploadPath);
           }

           try (InputStream inputStream = image.getInputStream()) {
               Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
               System.out.println("Image saved successfully: " + storageFileName);
           }

       } catch (Exception ex) {
           System.out.println("Exception: " + ex.getMessage());
           ex.printStackTrace();
       }

       // Converting ProductDto to Product and saving to the database
       Product product = new Product();
       product.setName(productDto.getName());
       product.setBrand(productDto.getBrand());
       product.setCategory(productDto.getCategory());
       product.setPrice(productDto.getPrice());
       product.setDescription(productDto.getDescription());
       product.setCreatedAt(createdAt);
       product.setImageFilename(storageFileName);

       // Saving product object in the database
       productsRepository.save(product);

   }

   public Product findProductById(int id){
       return productsRepository.findById(id).get();
   }

   public Product updateProducts(ProductDto productDto,int id){

       Product product = findProductById(id);
       // Deleting Old Image
       if(!productDto.getImageFilename().isEmpty())
       {
           String uploadDir = "BestStore/src/main/resources/static/images/";
           Path oldImagePath = Paths.get(uploadDir + product.getImageFilename());

           try
           {
               Files.delete(oldImagePath);
           }
           catch (Exception ex)
           {
               System.out.println("Exception :" + ex.getMessage());
           }

           // Saving the updated image file
           MultipartFile image = productDto.getImageFilename();
           Date createdAt = new Date();
           String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

           try(InputStream inputStream = image.getInputStream())
           {
               Files.copy(inputStream,Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
           }
           catch (Exception ex){
               System.out.println("Exception: "+ ex.getMessage());
           }
           // saving updated image in database
           product.setImageFilename(storageFileName);
       }

       product.setName(productDto.getName());
       product.setBrand(productDto.getBrand());
       product.setCategory(productDto.getCategory());
       product.setPrice(productDto.getPrice());
       product.setDescription(productDto.getDescription());

       // saving updated values
       productsRepository.save(product);

       return product;
   }


   public  void deleteProduct(int id){
       try
       {
           Product product = findProductById(id);
           Path imagePath = Paths.get("/resources/static/images/" + product.getImageFilename());
           try
           {
               Files.delete(imagePath);
           }
           catch (Exception ex){
               System.out.println("Exception: "+ ex.getMessage());
           }
           productsRepository.delete(product);

       }
       catch (Exception ex){
           System.out.println("Exception: "+ ex.getMessage());
       }
   }




}
