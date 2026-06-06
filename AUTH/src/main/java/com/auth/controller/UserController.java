package com.auth.controller;

import com.auth.exception.BadRequestException;
import com.auth.model.JwtTokenResponse;
import com.auth.model.LoginRequest;
import com.auth.model.User;
import com.auth.model.UserDto;
import com.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody User user){
        UserDto userDto =  userService.saveUser(user);
        return new ResponseEntity<>(userDto , HttpStatus.CREATED);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody LoginRequest loginRequest){

        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail() , loginRequest.getPassword()));

        if (authentication.isAuthenticated()){
            return ResponseEntity.ok(userService.generateToken(loginRequest.getEmail()));
        } else {
            throw new BadRequestException("Invalid email or password");
        }
    }

}
