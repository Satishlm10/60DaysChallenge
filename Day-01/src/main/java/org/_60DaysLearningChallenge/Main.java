package org._60DaysLearningChallenge;

import org._60DaysLearningChallenge.beans.Vehicle;
import org._60DaysLearningChallenge.beansConfig.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) 
    {
       /*
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

       // Bean creation using application context object to demonstrate concept of inversion of control

        Vehicle veh1 =  context.getBean("vehicle3",Vehicle.class);
        System.out.println("Understanding Dependency Injection and Inversion of Control.");
        System.out.println("Automatic object creation by Spring framework:"+ "\n" + "Spring bean:" + veh1.getName());

        System.out.println();


       // Demonstration of @Primary Annotation

        System.out.println("Understanding Primary annotation");
        Vehicle veh2 =  context.getBean(Vehicle.class);
        System.out.println("Automatic object creation by Spring framework:"+ "\n" + "Spring bean:" + veh2.getName());

        */

        /*

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        // Automatic bean creation using @Component and @ComponentScan Annotation
        Vehicle veh =  context.getBean(Vehicle.class);
        System.out.println("Understanding Dependency Injection and Inversion of Control.");
        veh.setName("Nissan GTR");
        System.out.println("Automatic object creation by Spring framework:"+ "\n" + "Spring bean:" + veh.getName());

         */
        /*

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        // Automatic bean creation using @Component and @ComponentScan Annotation
        Vehicle veh =  context.getBean(Vehicle.class);
        System.out.println("Understanding Dependency Injection and Inversion of Control.");
        // we don't have control over bean creation while using @Component Annotation
        // hence we required to set the value for the vehicle object manually
        //veh.setName("Nissan GTR");

        // but we can use @PostContruct Annotation to set values to object fields after creation of bean
        System.out.println("Automatic object creation by Spring framework:"+ "\n" + "Spring bean:" + veh.getName());

        */

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Vehicle veh =  context.getBean(Vehicle.class);
        System.out.println("Understanding Dependency Injection and Inversion of Control.");
        System.out.println("Automatic object creation by Spring framework:"+ "\n" + "Spring bean:" + veh.getName());
        System.out.println();
        // since spring framework is an external entity handling our programs object creation we are required
        // to destroy/close various object in spring context handling objects of I/0,database connections,etc
        // whenever the application is closed
        context.close();

    }
}