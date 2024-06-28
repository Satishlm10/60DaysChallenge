package com._daysLearningChallenge.SpringSecurityBasics.Filters;

import com._daysLearningChallenge.SpringSecurityBasics.Config.SecretConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JWTTokenValidatorFilter extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        String jwt = request.getHeader(SecretConstants.JWT_HEADER); // retrieving header with header name JWT_HEADER from request
        // if the request contains the header name specified in parameter then it is assigned to string jwt else the jwt will an empty string

        if(jwt != null)
        {
            try{
                // for validating the user's jwt token we need the original secret key
                SecretKey secretKey = Keys.hmacShaKeyFor(SecretConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parserBuilder() // validation of the users jwt token

                        .setSigningKey(secretKey)   // specifying the secret key stored in server against which the user request is compared to check validity of the jwt token

                        .build()    // initializing the validation process

                        .parseClaimsJws(jwt) // breaking(parsing) down the claims of the user from the user's http request header
                        // and the jwt token is validated by calculating the hash value stored in our backend and hash value of the user request jwt token

                        .getBody(); // retrieving the jwt token from the user's http request header

                /*
                    if up until now no error occurs the users jwt token is a valid token generated from our jwt token generation filter
                    then we extract claims from the jwt token and create a new authentication object for the user and store it in spring context

                    if error occurs then it will be throw to the catch block
                 */

                /*
                    During jwt token generation we created claims for the user using the authentication object of the user and assigned it to username and Authorities

                     .claim("username",authentication.getName())

                    .claim("Authorities",populateAuthorities(authentication.getAuthorities()))

                    so we are retrieving the claims of user for every user request made to access secured resource
                 */
                String username = String.valueOf(claims.get("username"));
                String authorities = (String) claims.get("Authorities");


                // creating a authentication object of the user for jwt token validation with username , password is null to keep it hidden, authorities of user
                // since we had created the string of authorities separated by comma we convert it to List of authorities and remove the comma using commaSeparatedStringToAuthorityList
                Authentication authentication = new UsernamePasswordAuthenticationToken(username,null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));


                // storing the valid jwt token verified authenticated user again the spring context
                // the jwt token will be valid until the expiration time specified during jwt token generation
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
            catch (Exception ex)
            {
                throw new BadRequestException("Invalid token received");
            }
        }


        filterChain.doFilter(request,response);

    }


    // specifying not to execute the validation of jwt token filter during the login operation of user (accessing login page)
    // because there is no point of validating the jwe token before the user logins
    // jwt token is generated after user login then the jwt token is validated for every request the user makes
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return !request.getServletPath().equals("/user");
    }
}


