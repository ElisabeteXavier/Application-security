package com.iftm.products.controller;

import com.iftm.products.domain.user.User;
import com.iftm.products.request.AuthenticationRequest;
import com.iftm.products.request.CreateUserRequest;
import com.iftm.products.response.AuthenticationResponse;
import com.iftm.products.infra.security.TokenService;
import com.iftm.products.response.CreateUserResponse;
import com.iftm.products.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authUser(@RequestBody AuthenticationRequest request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        var auth =  this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        var user = userService.createUser(request);
        var createdUser = new CreateUserResponse(user.getId(), user.getLogin());
        return ResponseEntity.status(201).body(createdUser);
    }

}
