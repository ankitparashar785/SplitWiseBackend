package com.app.splitwise.dtos;

import com.app.splitwise.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupDto {
    private String groupId;
    private String groupName;
    private String groupType;
    private long groupBudget;
    private List<User>groupUsers;
}
