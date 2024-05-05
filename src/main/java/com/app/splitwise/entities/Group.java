package com.app.splitwise.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "groupTable")
public class Group {

    @Id
    @Column(name = "groupId")
    private String id;
    @Column
    private String groupName;
    @Column
    private String groupType;
    @Column
    private long groupBudget;
    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    private List<User>users=new ArrayList<>();

}
