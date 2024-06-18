package com._daysLearningChallenge.SpringSecurityBasics.Controller;

import com._daysLearningChallenge.SpringSecurityBasics.Model.Customer;
import com._daysLearningChallenge.SpringSecurityBasics.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    // we cannot store simple plain text as password in database hence we have to use password encoding techniques
    // using BcryptPassword hashing technique
    @Autowired
    private PasswordEncoder passwordEncoder;


    // creating a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedCustomer = null;
        ResponseEntity response = null;
        try{
            // just before saving the password of the user before registration it should be hashed using the password encoder
            String hashedPassword = passwordEncoder.encode(customer.getPwd());
            // saving the customer object with hashed password
            customer.setPwd(hashedPassword);
            savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId()>0){
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given users details are successfully registered");
            }
        }
        catch (Exception ex){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to: "+ ex.getMessage());
        }
        return response;
    }
}
