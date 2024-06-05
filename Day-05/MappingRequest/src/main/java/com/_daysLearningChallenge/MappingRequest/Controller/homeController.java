package com._daysLearningChallenge.MappingRequest.Controller;

import com._daysLearningChallenge.MappingRequest.Dtos.detailsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class homeController {
    @GetMapping("/pathVariable")
    public String displayHome(){
        return "pathVariable.html";
    }
    @GetMapping("/pathVariable/{name}")
    public String pathVariable(@PathVariable String name, Model model){
        model.addAttribute("name",name);
        return "pathVariable.html";
    }

    @GetMapping("/requestParams")
    public String displayHomePage(){
        return "requestParam.html";
    }

    @GetMapping("/requestParam")
    public String requestParam(@RequestParam String name,@RequestParam String hobby,Model model){
        model.addAttribute("name",name);
        model.addAttribute("hobby",hobby);
        return "requestParam.html";
    }

    @GetMapping("/requestBodys")
    public String displayHomePages(){
        return "requestBody.html";
    }

    @PostMapping("/home")
    public String handleFormSubmission(@RequestBody detailsDto details, Model model) {
        model.addAttribute("name", details.getName());
        model.addAttribute("age", details.getAge());
        model.addAttribute("hobby", details.getHobby());
        model.addAttribute("address", details.getAddress());
        return "requestBody.html";
    }
}
