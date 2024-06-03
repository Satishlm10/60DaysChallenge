package com._daysLearningChallenge.Spring.Boot.Introduction.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/home")
    public String sayHello(){
        return "day-03";
    }
}
