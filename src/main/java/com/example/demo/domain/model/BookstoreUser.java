package com.example.demo.domain.model;

import com.example.demo.service.BookstoreUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static com.example.demo.config.CustomHeaderFilter.REQUIRED_HEADER_NAME_VALUE;

public class BookstoreUser implements Authentication {

    private final String name;

    private final UserDetails details;

    public BookstoreUser(String name, UserDetails details) {
        this.name = name;
        this.details = details;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return getName();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Do not do this!!!");
    }

    @Override
    public String getName() {
        return name;
    }
}
