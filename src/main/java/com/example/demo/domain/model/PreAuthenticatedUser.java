package com.example.demo.domain.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

public class PreAuthenticatedUser extends PreAuthenticatedAuthenticationToken {

    public PreAuthenticatedUser(Object aPrincipal, Object aCredentials) {
        super(aPrincipal, aCredentials);
    }

    public PreAuthenticatedUser(Object aPrincipal, Object aCredentials, Collection<? extends GrantedAuthority> anAuthorities) {
        super(aPrincipal, aCredentials, anAuthorities);
    }
}
