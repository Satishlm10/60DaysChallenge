package org._60DaysChallenge;

import org._60DaysChallenge.beans.Person;
import org._60DaysChallenge.beans.Vehicle;
import org._60DaysChallenge.beansConfig.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args)
    {
     // Establishing relation between Spring beans is know as wiring
     /*
        Wiring of bean can be done with following ways:
        1. With Method call (explicit wiring)
        2. by Method Parameter
        3. Autowired
            - Autowired in field
            - Autowired in setter method
            - Autowired with constructor
      */

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        // even when we are creating the bean of Person class first, since person class has dependency on vehicle class
        // the bean of vehicle class is created first then it is injected into person class
        // after dependency injection is complete the bean of person class is created.
        Person person = context.getBean(Person.class);
        Vehicle vehicle = context.getBean(Vehicle.class);

        System.out.println();

        person.setName("Magne Budo");
        vehicle.setName("RR");
        System.out.println("Person name from Spring Context:"+ person.getName());
        System.out.println("Vehicle name from Spring Context:"+ vehicle.getName());

        System.out.println();

        // Demo of explicit/manual wiring
        System.out.println("Autowiring with constructor ");
        System.out.println("Person bean with information form Vehicle bean. "+ "\n" +person.getVehicle());


    }
}