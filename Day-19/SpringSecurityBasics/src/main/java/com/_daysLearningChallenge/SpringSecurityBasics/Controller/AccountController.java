package com._daysLearningChallenge.SpringSecurityBasics.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public String getAccountDetail(){
        return "Here are the account details from db";
    }
}
