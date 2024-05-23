package com.shubh.restraunt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // Ensure this matches the name of your home HTML template (home.html)
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello, Secured!";
    }

}
