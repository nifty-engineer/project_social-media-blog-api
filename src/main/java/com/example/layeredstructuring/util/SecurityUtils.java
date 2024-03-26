package com.example.layeredstructuring.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUtils {

    public static User getSignedInUserAccount() {

        Object principal = SecurityContextHolder.getContext().getAuthentication()
          .getPrincipal();

        if(principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    public static String getRole() {

        User user = getSignedInUserAccount();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            return authority.getAuthority();
        }
        return null;
    }
}
