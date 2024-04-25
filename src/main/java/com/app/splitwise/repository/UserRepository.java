package com.app.splitwise.repository;


import com.app.splitwise.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String userEmail) throws UsernameNotFoundException;
}
