package org._60DaysLearningChallenge.beansConfig;

import org._60DaysLearningChallenge.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "org._60DaysLearningChallenge")
public class ProjectConfig {
    /*
    @Bean
    Vehicle vehicle1(){
        var veh = new Vehicle();
        veh.setName("RR");
        return veh;
    }

    @Primary
    @Bean
    Vehicle vehicle2(){
        var veh = new Vehicle();
        veh.setName("BMW");
        return veh;
    }
    @Bean
    Vehicle vehicle3(){
        var veh = new Vehicle();
        veh.setName("VW");
        return veh;
    }

     */

}
