package com.example.layeredstructuring.repository;

import com.example.layeredstructuring.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    UserAccount findByEmail(String email);
}
