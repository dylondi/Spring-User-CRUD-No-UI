package com.example.usercrudnoui.controller;

import com.example.usercrudnoui.model.User;
import com.example.usercrudnoui.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Processes incoming CRUD User REST requests.
 */
@RestController
public class UserController {

    /**
     * Constructor Dependency Injection
     */
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a List of all Users from UserService's listAll() method.
     * @return List of All Users
     */
    @GetMapping("/users")
    public List<User> findAllUsers(){
        return userService.listAll();
    }

    /**
     * Creates a new with UserService's createUser() method and the User
     * Object passed in the RequestBody.
     * @param user
     * @return New User Object
     */
    @PostMapping("/users/create")
    public User createNewUser(@RequestBody User user){
        return userService.createUser(user);
    }

    /**
     * Retrieves a User Object with specified id.
     * @param id
     * @return User Object
     */
    @GetMapping("/users/user/{id}")
    public User findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    /**
     * Updates a User Object with UserService's updateUser() method and the
     * User Object passed in the RequestBody.
     * @param user
     * @return Updated User Object
     */
    @PutMapping("/users/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * Deletes a User Object with specified id passed as method parameter.
     * @param id
     * @return String containing message
     */
    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }

}
