package com.example.layeredstructuring.security;

import com.example.layeredstructuring.entity.UserAccount;
import com.example.layeredstructuring.repository.UserAccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class UserAccountDetailsService implements UserDetailsService {

    UserAccountRepository userAccountRepository;

    public UserAccountDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserAccount userAccount = userAccountRepository.findByEmail(email);

        if(userAccount != null) {
            User authenticateduser = new User(
              userAccount.getEmail(),
              userAccount.getPassword(),
              userAccount.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList())
            );
            return authenticateduser;
        }
        else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
