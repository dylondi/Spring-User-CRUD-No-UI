package com.example.usercrudnoui.repository;

import com.example.usercrudnoui.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
