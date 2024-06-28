package com._daysLearningChallenge.SpringSecurityBasics.Filters;

import com._daysLearningChallenge.SpringSecurityBasics.Config.SecretConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        /*
            -> since first user login using their username and password
            -> The user is authenticated and the authentication objects of the user is stored in Spring Context
            -> so first we retrieve an authentication object from spring context
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null)
        {
            SecretKey secretKey = Keys.hmacShaKeyFor(SecretConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8)); // creating a secret key using a key stored in our backend

            String jwt = Jwts.builder()  // creating jwt token
                        .setIssuer("SecuredBank") // name of person or organization who is issuing the jwt token

                        .setSubject("JWT Token")  // subject for what this jwt token is going to be used as

                    .claim("username",authentication.getName()) // assigning the authenticated user with the jwt token being built (claim of the authenticated user)

                    .claim("Authorities",populateAuthorities(authentication.getAuthorities()))   // assigning authorities of user in jwt token being built

                    .setIssuedAt(new Date()) // date,time when the jwt token was assigned to the authenticated user

                    .setExpiration(new Date((new Date()).getTime()+30000000))    // expiration time of jwt token // 30000000 milliseconds = around 8 hours

                    .signWith(secretKey).compact(); // finally signing the jwt token being built with secret key

                    response.setHeader(SecretConstants.JWT_HEADER,jwt);


        }


    }


    // specifying not to execute the generation of jwt token filter during the login operation of user (accessing login page)
    // because there is no point in generating the jwt token even before the user logs in and before the authentication has happened
    // the jwt token is generated after the user logins and a authentication object is stored in spring context for the user
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return !request.getServletPath().equals("/user");
    }


    /*
        since there can be multiple roles of a user hence we create function
        where it takes a collection of GrantedAuthority as input parameter
        and joins then using ,
        so if a user has user and admin role
        this function will get both roles from the collection of GrantedAuthority
        and convert them into a String "user,admin"

     */

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection)
    {
        Set<String> authoritiesSet = new HashSet<>();
        for(GrantedAuthority authority : collection)
        {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",",authoritiesSet);
    }
}
