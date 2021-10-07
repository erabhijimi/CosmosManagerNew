package com.cosmos.controller;

import com.cosmos.error.PasswordError;
import com.cosmos.pojo.JWTRequest;
import com.cosmos.pojo.JWTResponse;
import com.cosmos.model.User;
import com.cosmos.pojo.ResponseEntity;
import com.cosmos.service.UserService;
import com.cosmos.utility.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtility;
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String checkLogin(){
        log.info("Login method called with get request...");
        return "Success";
    }
    @GetMapping("/login/admin")
    public String checkAdminLogin(){
        log.info("Login method called with get request for admin...");
        return "Success page for admin";
    }
    @PostMapping("/login")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest)throws Exception{
        log.info("Post method called...");
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        log.info("Got details"+userDetails);
        if(userDetails==null){
            log.info("Password Not matching");
            throw new PasswordError("Password Not matching");
        }
        String token = jwtUtility.generateToken(userDetails);
        return new JWTResponse(HttpStatus.OK,token);
    }
    @GetMapping("/register")
    public String getRegisterPage(){
        log.info("/register called");
        return "Can access register Page";
    }
    @PostMapping("/register")
    public ResponseEntity saveUserRegistration(@RequestBody User user){
        return userService.saveUserRegistration(user);
    }
    @PutMapping("/register/{username}")
    public ResponseEntity forgotPassword(@PathVariable Long username,@RequestBody User user){
        return userService.forgotPassword(username,user);
    }

}
