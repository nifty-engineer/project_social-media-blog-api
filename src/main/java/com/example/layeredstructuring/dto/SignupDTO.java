package com.example.layeredstructuring.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {

    private Integer signup_id;

    @NotBlank(message = "First Name must have a value")
    private String firstName;

    @NotBlank(message = "Last Name must have a value")
    private String lastName;

    @NotBlank(message = "Email Address must have a value")
    private String email;

    @NotBlank(message = "Password must have a value")
    private String password;
}
