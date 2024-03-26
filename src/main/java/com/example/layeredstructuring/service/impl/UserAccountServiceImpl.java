package com.example.layeredstructuring.service.impl;

import com.example.layeredstructuring.dto.SignupDTO;
import com.example.layeredstructuring.entity.UserAccount;
import com.example.layeredstructuring.entity.UserRole;
import com.example.layeredstructuring.repository.UserAccountRepository;
import com.example.layeredstructuring.repository.UserRoleRepository;
import com.example.layeredstructuring.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountRepository userAccountRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository,
                                  UserRoleRepository userRoleRepository,
                                  PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUserAccount(SignupDTO signupDTO) {

        UserAccount userAccount = new UserAccount();
        userAccount.setFullname(signupDTO.getFirstName() + " " +
                        signupDTO.getLastName());
        userAccount.setEmail(signupDTO.getEmail());
        // long term goal is to use spring security to encrypt the password
        userAccount.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        UserRole userRole = userRoleRepository.findByName("guest");
        userAccount.setRoles(Arrays.asList(userRole));
        userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount findByEmail(String email) {

        return userAccountRepository.findByEmail(email);
    }
}
