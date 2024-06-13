package com._daysLearningChallenge.BestStore.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    int resourceId;
    public ResourceNotFoundException(String resourceName, int resourceId){

        super(String.format("%s with product id %s is not in the product list",resourceName,resourceId));
        this.resourceName = resourceName;
        this.resourceId = resourceId;
    }
}
