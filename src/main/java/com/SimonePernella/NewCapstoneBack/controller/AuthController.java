package com.SimonePernella.NewCapstoneBack.controller;

import com.SimonePernella.NewCapstoneBack.dto.UserDTO;
import com.SimonePernella.NewCapstoneBack.dto.UserLoginDTO;
import com.SimonePernella.NewCapstoneBack.dto.UserSuccessLoginDTO;
import com.SimonePernella.NewCapstoneBack.entities.User;
import com.SimonePernella.NewCapstoneBack.exception.BadRequestException;
import com.SimonePernella.NewCapstoneBack.service.AuthService;
import com.SimonePernella.NewCapstoneBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//endpoint principale
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserSuccessLoginDTO login(@RequestBody UserLoginDTO body) throws Exception {
        return new UserSuccessLoginDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated UserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("", validation.getAllErrors());
        } else {
            System.out.println(body);
            User newUser = authService.save(body);
            return newUser;
        }
    }
    }


