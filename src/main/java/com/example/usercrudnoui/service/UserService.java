package com.example.usercrudnoui.service;

import com.example.usercrudnoui.model.User;
import com.example.usercrudnoui.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> listAll(){
        return repository.findAll();
    }

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User findUserById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public String deleteUserById(Integer id) {

        repository.deleteById(id);
        return "User removed";
    }


    public User updateUser(User user){
        User existingUser = repository.findById(user.getId()).orElse(null);
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(existingUser);
    }
}
