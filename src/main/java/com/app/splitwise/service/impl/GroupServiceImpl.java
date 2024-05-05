package com.app.splitwise.service.impl;

import com.app.splitwise.dtos.GroupDto;
import com.app.splitwise.entities.Group;
import com.app.splitwise.entities.User;
import com.app.splitwise.repository.GroupRepository;
import com.app.splitwise.repository.UserRepository;
import com.app.splitwise.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void createGroup(GroupDto groupDto) {
        String s = UUID.randomUUID().toString();
        groupDto.setGroupId(s);
        Group group=modelMapper.map(groupDto, Group.class);
        groupRepository.save(group);
    }

    @Override
    public void addUserToGroup(String userId,String groupId) {
        System.out.println(userId);
        System.out.println(groupId);
        User user = userRepository.findById(userId).orElseThrow();
        System.out.println(user.toString());
        Group group=groupRepository.findById(groupId).orElseThrow();
        user.setGroup(group);
        userRepository.save(user);
    }
}
