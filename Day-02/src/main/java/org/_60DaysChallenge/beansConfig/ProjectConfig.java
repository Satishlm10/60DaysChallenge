package org._60DaysChallenge.beansConfig;

import org._60DaysChallenge.beans.Person;
import org._60DaysChallenge.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "org._60DaysChallenge")
public class ProjectConfig {


    // used when manually creating beans
    // when we use @Component in Java pojo class automatically beans of the pojo class are created
    // so we don't have to manually establish relationship between them by manually wiring the beans
    // rather we use @Autowired annotation to automatically wire the beans
/*
@Bean
    Vehicle vehicle(){
        Vehicle veh = new Vehicle();
        veh.setName("RR");
        return veh;
    }

    // since object creation of person class is dependent upon vehicle class we explicitly inject the dependency
    @Bean
    Person person(Vehicle vehicle)  // injection of dependency through method parameter
    {
        Person person = new Person();
        person.setName("Magne Budo");



            // explicit dependency injection or wiring through method call
             person.setVehicle(vehicle());


    // explicit dependency injection or wiring of bean through method parameter
        person.setVehicle(vehicle);


        return person;
}
 */






}
