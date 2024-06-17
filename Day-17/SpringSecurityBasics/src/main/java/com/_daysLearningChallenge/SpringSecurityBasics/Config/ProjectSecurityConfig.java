package com._daysLearningChallenge.SpringSecurityBasics.Config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    // To override default configuration of spring security we make our custom bean of SecurityFilterChain
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // we may want some url to be secured and some to be public
        // using methods like authenticated,permitAll,denyAll we can define our custom security for the urls
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myBalance","/myLoans","/myCard").authenticated()
                .requestMatchers("/notices","/contact","/register").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/register"));
        return http.build();
    }


/*
// we can define and manage authentication using following approaches:
    1. InMemoryUserDetailsService
    2. JdbcUserDetailsManager
    3. Other default Service/Manager provided by Spring Security
    4. Our custom service which is the implementation of UserDetailsService
 */

/*
    // Not production recommended approach
    // defining and manging authorization using InMemoryDetailsManager Bean
    @Bean
    InMemoryUserDetailsManager userDetailService(){
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .authorities("user")
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }

 */

    // using default JdbcUserDetailsManger configuration to connect to database and authenticate the user with default tables: users and authorities
    /*
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

     */

    // Using custom database table to authenticate the user go to -> SecuredBankUserDetails class

    // to store the password using some encoding technique
    // NoOppasswordEncoder -> no encoding technique used to store password in database
    /*
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

     */

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}

