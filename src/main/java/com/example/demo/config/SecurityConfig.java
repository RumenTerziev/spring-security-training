package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final BookstoreBasicAuthProvider bookstoreBasicAuthProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/books").permitAll()
                        .requestMatchers("/orders/header").authenticated()
                        .requestMatchers("/orders/basic").authenticated()
                        .requestMatchers("/orders/header-new").authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(IF_REQUIRED)
                        .maximumSessions(1))
                .addFilterBefore(customHeaderFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(requestHeaderAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .authenticationManager(authManager())
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public CustomHeaderFilter customHeaderFilter() {
        return new CustomHeaderFilter();
    }

    @Bean
    public AuthenticationManager authManager() {
        return new ProviderManager(Collections.singletonList(bookstoreBasicAuthProvider));
    }

    @Bean
    public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() {
        RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
        filter.setPrincipalRequestHeader("AUTH_KEY");
        filter.setCredentialsRequestHeader("RUMBATA");
        filter.setExceptionIfHeaderMissing(true);
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/orders/header-new"));
        filter.setAuthenticationManager(authManager());
        return filter;
    }
}
