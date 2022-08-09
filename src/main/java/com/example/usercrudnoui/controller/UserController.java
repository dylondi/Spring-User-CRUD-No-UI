package com.example.usercrudnoui.controller;

import com.example.usercrudnoui.exceptions.ConstraintsViolationException;
import com.example.usercrudnoui.exceptions.EntityNotFoundException;
import com.example.usercrudnoui.model.User;
import com.example.usercrudnoui.service.DefaultUserService;
import com.example.usercrudnoui.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Processes incoming CRUD User REST requests.
 */
@RestController
@RequestMapping("users")
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
    @GetMapping()
    public List<User> findAllUsers(){
        return userService.listAll();
    }

    /**
     * Creates a new with UserService's createUser() method and the User
     * Object passed in the RequestBody.
     * @param user
     * @return New User Object
     */
    @PostMapping("/create")
    public User createNewUser(@RequestBody User user) throws ConstraintsViolationException {
        try {
            return userService.createUser(user);
        } catch (ConstraintsViolationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a User Object with specified id.
     * @param id
     * @return User Object
     */
    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Integer id){
        try {
            return userService.findUserById(id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates a User Object with UserService's updateUser() method and the
     * User Object passed in the RequestBody.
     * @param user
     * @return Updated User Object
     */
    @PutMapping("/update")
    public User updateUser(@RequestBody User user) throws EntityNotFoundException {
        return userService.updateUser(user);
    }

    /**
     * Deletes a User Object with specified id passed as method parameter.
     * @param id
     * @return String containing message
     */
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }

}
