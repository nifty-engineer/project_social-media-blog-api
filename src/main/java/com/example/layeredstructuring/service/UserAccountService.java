package com.example.layeredstructuring.service;

import com.example.layeredstructuring.dto.SignupDTO;
import com.example.layeredstructuring.entity.UserAccount;

public interface UserAccountService {

    void saveUserAccount (SignupDTO signupDTO);

    UserAccount findByEmail(String email);
}
