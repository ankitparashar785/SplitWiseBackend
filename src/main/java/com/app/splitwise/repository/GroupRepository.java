package com.app.splitwise.repository;

import com.app.splitwise.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,String> {
}
