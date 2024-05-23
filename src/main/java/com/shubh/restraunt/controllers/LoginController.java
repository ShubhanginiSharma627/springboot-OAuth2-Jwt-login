package com.shubh.restraunt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shubh.restraunt.dtos.AuthenticationRequest;
import com.shubh.restraunt.dtos.UserRegistrationRequest;

/**
 *
 * @author Shubu
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userloginRequest", new AuthenticationRequest());
        return "login";
    }

    @RequestMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationRequest", new UserRegistrationRequest());
        return "register";
    }

    @PostMapping("/login")
    public String loginUser(AuthenticationRequest request, Model model) {
        try {

            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}
