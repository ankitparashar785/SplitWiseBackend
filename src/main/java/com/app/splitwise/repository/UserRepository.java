package com.app.splitwise.repository;


import com.app.splitwise.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}
