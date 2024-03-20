package com.example.demo.repository;

import com.example.demo.domain.model.BokstoreUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookstoreUserRepository extends JpaRepository<BokstoreUserDetails, Long> {
    BokstoreUserDetails findByUsername(String username);
}
