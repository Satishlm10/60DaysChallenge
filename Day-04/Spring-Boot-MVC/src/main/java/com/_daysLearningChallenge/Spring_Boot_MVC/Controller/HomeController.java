package com._daysLearningChallenge.Spring_Boot_MVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController
{

    @GetMapping("/home/{name}")
    public String displayHome(@PathVariable String name, Model model){
        model.addAttribute("name",name);
        return "home.html";

    }

}
