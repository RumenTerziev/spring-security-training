package com.example.demo.service;

import com.example.demo.domain.model.BokstoreUserDetails;
import com.example.demo.repository.BookstoreUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookstoreUserDetailsService implements UserDetailsService {

    private final BookstoreUserRepository bookstoreUserRepository;

    private final PasswordEncoder passwordEncoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    private static final String FIRST_USER_USERNAME = "Pesho";

    private static final String FIRST_USER_PASSWORD = "4321a";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return bookstoreUserRepository.findByUsername(username);
    }

    public void initializeUsers() {
        bookstoreUserRepository.save(new BokstoreUserDetails(FIRST_USER_USERNAME, passwordEncoder.encode(FIRST_USER_PASSWORD)));
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
