package com.example.layeredstructuring.controller;

import com.example.layeredstructuring.dto.SignupDTO;
import com.example.layeredstructuring.entity.UserAccount;
import com.example.layeredstructuring.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private UserAccountService userAccountService;

    public AuthController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/signin")
    public String showSigninForm() {

        return "signin";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {

        // this empty object holds form data
        SignupDTO signupDTO = new SignupDTO();
        model.addAttribute("signupDTO", signupDTO);
        return "signup";
    }

    @PostMapping("/signup/save")
    public String signup(@Valid @ModelAttribute("signupDTO") SignupDTO signupDTO,
                         BindingResult bindingResult,
                         Model model) {

        UserAccount existingUser = userAccountService.findByEmail(signupDTO.getEmail());
        if(existingUser != null && !existingUser.getEmail().isBlank()) {
            bindingResult.rejectValue("email", null, "An account with this email address already exist");
        }

        if(bindingResult.hasErrors()) {
            model.addAttribute("signupDTO", signupDTO);
            return "signup";
        }

        userAccountService.saveUserAccount(signupDTO);
        return "redirect:/signup?success";
    }

}
