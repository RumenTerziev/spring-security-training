package com.example.demo.service;

import com.example.demo.domain.model.BokstoreUserDetails;
import com.example.demo.repository.BookstoreUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookstoreUserDetailsService implements UserDetailsService {

    private final BookstoreUserRepository bookstoreUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return bookstoreUserRepository.findByUsername(username);
    }

    public void initializeUsers() {
        bookstoreUserRepository.save(new BokstoreUserDetails("Pesho", "4321a"));
    }
}
