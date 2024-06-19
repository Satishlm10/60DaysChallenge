package com._daysLearningChallenge.SpringSecurityBasics.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {
    @GetMapping("/myCard")
    public String getCardDetail(){
        return "Here are the card details from db";
    }
}
