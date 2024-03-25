package com.example.demo.config;

import com.example.demo.domain.model.BookstoreUser;
import com.example.demo.service.PreAuthenticationUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomPreAuthenticatedAuthenticationProvider extends PreAuthenticatedAuthenticationProvider {

    private final PreAuthenticationUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = (String) authentication.getPrincipal();
        String headerValue = authentication.getPrincipal().toString();
        if (headerValue != null && headerValue.equals(name)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            new BookstoreUser(name, userDetails);
        }
        return null;
    }
}
