package com.example.demo.repository;

import com.example.demo.domain.model.BookstoreUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookstoreUserRepository extends JpaRepository<BookstoreUserDetails, Long> {
    BookstoreUserDetails findByUsername(String username);
}
