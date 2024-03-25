package com.example.demo.service;

import com.example.demo.domain.model.BookstoreUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PreAuthenticationUserDetailsService implements UserDetailsService {

    private static final String REQUIRED_HEADER_NAME = "AUTH_KEY";

    private static final String REQUIRED_HEADER_NAME_VALUE = "RUMEN";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new BookstoreUserDetails(REQUIRED_HEADER_NAME, REQUIRED_HEADER_NAME_VALUE);
    }
}
