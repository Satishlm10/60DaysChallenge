package com._daysLearningChallenge.BestStore.Entities.Dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {

    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Brand name is required")
    private String brand;
    @NotEmpty(message = "Category name is required")
    private String category;
    @Min(0)
    private double price;
    @Size(min = 10,message = "Description should at least contain 10 characters")
    @Size(max = 500,message = "Description message exceeds limit of 500 characters")
    private String description;

    private MultipartFile imageFilename;
}
