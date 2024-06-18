package com._daysLearningChallenge.SpringSecurityBasics.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {
    @GetMapping("/myLoans")
    public String getLoanDetail(){
        return "Here are the loan details from db";
    }
}
