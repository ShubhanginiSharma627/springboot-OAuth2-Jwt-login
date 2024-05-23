package com.shubh.restraunt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shubh.restraunt.dtos.AuthenticationRequest;
import com.shubh.restraunt.dtos.UserRegistrationRequest;
import com.shubh.restraunt.service.UserDetailsServiceImpl;

@Controller
public class RegistrationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationRequest", new UserRegistrationRequest());
        return "register";
    }

    @RequestMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userloginRequest", new AuthenticationRequest());
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(UserRegistrationRequest request, Model model) {
        try {
            userDetailsService.saveNewUser(request);
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
