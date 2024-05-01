package com.app.splitwise.controller;

import com.app.splitwise.dtos.JwtRequest;
import com.app.splitwise.dtos.JwtResponse;
import com.app.splitwise.dtos.UserDto;
import com.app.splitwise.security.JwtHelper;
import com.app.splitwise.service.impl.CustomUserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDetailsService userDetailsService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse>loginUser(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getEmail(),request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        System.out.println("Test");
        String token = jwtHelper.generateToken(userDetails);
        JwtResponse jwtResponse=new JwtResponse();
        jwtResponse.setJwtToken(token);
        jwtResponse.setUserDto(modelMapper.map(userDetails, UserDto.class));
       return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(email,password);

        try{
            manager.authenticate(authenticationToken);
            System.out.println("inside do authenticate");
        }catch (BadCredentialsException e){
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
