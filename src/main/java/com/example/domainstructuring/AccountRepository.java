package com.example.domainstructuring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    Optional<Account> findByUsernameAndPassword(String username, String password);
    
}

