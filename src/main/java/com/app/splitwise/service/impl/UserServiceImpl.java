package com.app.splitwise.service.impl;

import com.app.splitwise.dtos.UserDto;
import com.app.splitwise.entities.User;
import com.app.splitwise.repository.UserRepository;
import com.app.splitwise.service.UserService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto registerUser(UserDto userDto) {
        String s = UUID.randomUUID().toString();
        userDto.setId(s);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setConfirmPassword(passwordEncoder.encode(userDto.getPassword()));
        User user=modelMapper.map(userDto, User.class);
        userRepository.save(user);
        return userDto;
    }

}
