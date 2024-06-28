package com._daysLearningChallenge.SpringSecurityBasics.Config;

import com._daysLearningChallenge.SpringSecurityBasics.Model.Authority;
import com._daysLearningChallenge.SpringSecurityBasics.Model.Customer;
import com._daysLearningChallenge.SpringSecurityBasics.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SecuredBankAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Customer> customers = customerRepository.findByEmail(email);
        if(customers.size()>0)
        {
            if (passwordEncoder.matches(password,customers.get(0).getPwd()))
            {
                return new UsernamePasswordAuthenticationToken(email,password,getGrantedAuthorities(customers.get(0).getAuthorities()));
            }
            else
            {
                throw new BadCredentialsException("Invalid Password");
            }
        }
        else
        {
            throw new BadCredentialsException("User not registered");
        }

    }


    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
