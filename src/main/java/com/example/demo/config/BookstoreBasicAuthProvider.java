package com.example.demo.config;

import com.example.demo.service.BookstoreUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookstoreBasicAuthProvider implements AuthenticationProvider {

    private final BookstoreUserDetailsService bookstoreUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication contextAuth = SecurityContextHolder.getContext().getAuthentication();
        if (contextAuth != null) {
            return contextAuth;
        }
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        UserDetails userDetails = bookstoreUserDetailsService.loadUserByUsername(authentication.getName());
        if (userDetails == null || !bookstoreUserDetailsService.matches(password, userDetails.getPassword())) {
            return null;
        }
        return passwordAuthenticationToken(name, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class) || authentication.equals(BookstoreUser.class);
    }

    private static UsernamePasswordAuthenticationToken passwordAuthenticationToken(String name, String password) {
        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }
}
