package org._60DaysChallenge.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {

    public  Vehicle(){
        System.out.println("Vehicle Bean created by Spring");
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello(){
        System.out.println("Hello from Vehicle Bean");
    }
    @Override
    public String toString() {
        return "Vehicle name is:"+ this.name;
    }
}
