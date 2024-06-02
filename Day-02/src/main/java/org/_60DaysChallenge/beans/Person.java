package org._60DaysChallenge.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    // autowiring using constructor
    @Autowired // in constructor injection writing @Autowired isn't mandatory but mention it for readability
    public  Person(Vehicle vehicle) // injecting dependency through constructor, recommended when there is only constructor of the class
    {
        System.out.println("Person Bean created by Spring");
        this.vehicle = vehicle;
    }
    private String name;
    // the Person bean is dependent upon the Vehicle bean as Person class has dependency on Vehicle class

    // Dependency injection using Autowiring concept
    //@Autowired
    private Vehicle vehicle;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /*

    public String getVehicle() {
        return vehicle.getName();
    }

     */

    // since here the object is being returned whenever the Person class invokes the vehicle class's toString method of vehicle class is invoked
    public Vehicle getVehicle(){
        return vehicle;
    }

    //@Autowired // autowiring in setter method allows the vehilce field to be final
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


}
