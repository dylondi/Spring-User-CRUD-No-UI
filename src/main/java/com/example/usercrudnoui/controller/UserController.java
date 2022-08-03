package com.example.usercrudnoui.controller;

import com.example.usercrudnoui.model.User;
import com.example.usercrudnoui.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAllUsers(){
        return userService.listAll();
    }

    @PostMapping("/users/create")
    public User createNewUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/users/user/{id}")
    public User findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @PutMapping("/users/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }

}
