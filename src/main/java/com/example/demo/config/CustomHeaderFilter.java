package com.example.demo.config;

import com.example.demo.domain.model.BookstoreUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public class CustomHeaderFilter extends OncePerRequestFilter {

    private static final String REQUIRED_HEADER_NAME = "AUTH_NAME";
    public static final String REQUIRED_HEADER_NAME_VALUE = "RUMEN";
    private static final String REQUIRED_HEADER_PASS = "AUTH_PASS";
    private static final String REQUIRED_HEADER_PASS_VALUE = "TOPSECRET";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (!request.getRequestURI().equals("/orders/header") || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String usernameHeaderValue = request.getHeader(REQUIRED_HEADER_NAME);
        String passwordHeaderValue = request.getHeader(REQUIRED_HEADER_PASS);

        if (isHeaderValueInvalid(usernameHeaderValue, REQUIRED_HEADER_NAME_VALUE) ||
                isHeaderValueInvalid(passwordHeaderValue, REQUIRED_HEADER_PASS_VALUE)) {
            response.setStatus(SC_UNAUTHORIZED);
            response.getWriter().println("Custom header missing or invalid");
            return;
        }
        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(new BookstoreUser(REQUIRED_HEADER_PASS_VALUE, null));
        SecurityContextHolder.setContext(newContext);
        filterChain.doFilter(request, response);
    }

    private boolean isHeaderValueInvalid(String headerValue, String expectedValue) {
        return headerValue == null || !headerValue.equals(expectedValue);
    }
}
