package com._daysLearningChallenge.SpringSecurityBasics.Config;



import com._daysLearningChallenge.SpringSecurityBasics.Filters.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import java.util.function.Supplier;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    // To override default configuration of spring security we make our custom bean of SecurityFilterChain
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {


        // generating csrf token to avoid csrf attack from already registered user
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");



        // we may want some url to be secured and some to be public
        // using methods like authenticated,permitAll,denyAll we can define our custom security for the urls
        http.securityContext(securityContext -> securityContext.requireExplicitSave(false))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))

                .authorizeHttpRequests((requests) -> requests

                 // used for authentication only
                //.requestMatchers("/myAccount","/myBalance","/myLoans","/myCards","/user").authenticated()


                 // for authentication and authorization we can use the following code
                        // authority based authorization
                       /*
                        .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
                            .requestMatchers("/myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
                        .requestMatchers("/myLoans").hasAuthority("VIEWLOANS")
                        .requestMatchers("/myCards").hasAuthority("VIEWCARDS")
                        */

                        // role-base authorization we can do as the following code
                        .requestMatchers("/myAccount").hasRole("USER")
                        .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/myLoans").hasRole("USER")
                        .requestMatchers("/myCards").hasRole("USER")

                        // customer with customer id 1 has both user and admin role hence all endpoints can be accessed by customer with id 1
                        .requestMatchers("/user").authenticated()

                .requestMatchers("/notices","/contact","/register").permitAll());

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults())
                // spirng security enforces csrf protection by default.

                // only disable csrf for testing purpose rather we should handle the protection configuration ourselves
                // .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())


                /* /register is a post api so csrf protection will protect this url
                    but since user is going to register for the first item
                    it doesn't require csrf protection but other post and put requests will require csrf protection if
                    the user is already registered and wants to perform post or put request
                 */
                // simple lambda expression to ignore csrf protection for the following api paths
        // .csrf(csrf -> csrf.ignoringRequestMatchers("/register","/contact"));

        // using crsf token to accept only post and put request generated from my own UI application only
                // request handler is an object of CsrfTokenRequestAttributeHandler which generate a token
                // the request handler object is passed as parameter to csrfTokenRequestHandler
                // then the token is stored as a cookie then it will be send to the ui application which will store it in the browser


                // manually configuring csrf protection to handle using a csrf token and allow post and put request
                // whenever the request is from our UI application
                .csrf((csrf)->csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/register","/contact")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);


        return http.build();
    }



    // handling cors policy using a bean of corsFilter
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // Allow credentials
        config.addAllowedOriginPattern("http://localhost:4200");// Allow all origins, adjust as needed
        config.addAllowedOriginPattern("http://localhost:8080");
        config.addAllowedHeader("*"); // Allow all headers, adjust as needed
        config.addAllowedMethod("*"); // Allow all methods (GET, POST, etc), adjust as needed

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
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

