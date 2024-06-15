package com._daysLearningChallenge.SpringSecurityBasics.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {
    @GetMapping("/myBalance")
    public String getBalanceDetail(){
        return "Here are the Balance details from db";
    }
}
