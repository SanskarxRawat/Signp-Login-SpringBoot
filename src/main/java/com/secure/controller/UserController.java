package com.secure.controller;

import com.secure.dto.response.AuthenticationResponse;
import com.secure.dto.request.LoginDTO;
import com.secure.dto.request.SignupDTO;
import com.secure.dto.request.UserDTO;
import com.secure.services.auth.AuthService;
import com.secure.services.jwt.UserDetailsServiceImpl;
import com.secure.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@Slf4j
@RestController
public class UserController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
       log.info("Sign Up Request :{}",signupDTO);
       UserDTO createdUser = authService.createUser(signupDTO);
       if (Objects.isNull(createdUser)){
           return new ResponseEntity<>("User not created, come again later!", HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody LoginDTO loginDTO, HttpServletResponse response) throws Throwable {
        log.info("Login Request :{}",loginDTO);
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        if(authentication.isAuthenticated()){
             UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());
             String jwt = jwtUtil.generateToken(userDetails.getUsername());
             return new AuthenticationResponse(jwt);
        }else {
            throw new UsernameNotFoundException("Invalid User request!!");
        }
    }

}
