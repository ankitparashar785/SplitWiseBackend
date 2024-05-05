package com.app.splitwise.service;


import com.app.splitwise.dtos.GroupDto;

public interface GroupService {
     void createGroup(GroupDto groupDto);
     void addUserToGroup(String userId,String groupId);
}
