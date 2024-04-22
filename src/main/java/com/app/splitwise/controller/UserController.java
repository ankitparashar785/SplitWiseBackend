package com.app.splitwise.controller;

import com.app.splitwise.dtos.UserDto;
import com.app.splitwise.entities.User;
import com.app.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
   private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userData){
        UserDto userDto = userService.registerUser(userData);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
