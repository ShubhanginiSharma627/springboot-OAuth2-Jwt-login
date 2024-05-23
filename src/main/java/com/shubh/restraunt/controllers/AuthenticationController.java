package com.shubh.restraunt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.restraunt.dtos.AuthenticationRequest;
import com.shubh.restraunt.dtos.AuthenticationResponse;
import com.shubh.restraunt.dtos.UserRegistrationRequest;
import com.shubh.restraunt.dtos.UserRegistrationResponse;
import com.shubh.restraunt.model.User;
import com.shubh.restraunt.service.UserDetailsServiceImpl;
import com.shubh.restraunt.utils.JwtUtil;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());

            final String jwt = jwtUtil.generateToken(userDetails);
            AuthenticationResponse loginResponse = new AuthenticationResponse(jwt);
            return ResponseEntity.ok(loginResponse);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during authentication");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody UserRegistrationRequest request) {
        User user = userDetailsService.saveNewUser(request);
        final String jwt = jwtUtil.generateToken(user);
        System.out.println("Received authentication request for jwt: " + user);
        UserRegistrationResponse Response = new UserRegistrationResponse(jwt, user.getUsername(), user.getPassword());
        return ResponseEntity.ok(Response);
    }
}
