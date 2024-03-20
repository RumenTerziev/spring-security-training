package com.example.demo.init;

import com.example.demo.service.BookstoreUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {

    private final BookstoreUserDetailsService bookstoreUserDetailsService;

    @Override
    public void run(String... args) throws Exception {
        bookstoreUserDetailsService.initializeUsers();
    }
}
