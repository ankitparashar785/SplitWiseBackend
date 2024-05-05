package com.app.splitwise.controller;

import com.app.splitwise.dtos.AddUserToGroupDto;
import com.app.splitwise.dtos.GroupDto;
import com.app.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @PostMapping("/create")
    private ResponseEntity<String> createGroup(@RequestBody GroupDto groupData){
        groupService.createGroup(groupData);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }
    @PostMapping("/addUser")
    private ResponseEntity<String>adduserToGroup(@RequestBody AddUserToGroupDto addUserToGroupDto){
        groupService.addUserToGroup(addUserToGroupDto.getUserId(),addUserToGroupDto.getGroupId());
        return new ResponseEntity<>("User is Added",HttpStatus.OK);
    }
}
