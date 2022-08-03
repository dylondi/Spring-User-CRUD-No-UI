package com.example.usercrudnoui.service;

import com.example.usercrudnoui.model.User;
import com.example.usercrudnoui.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring Service Class containing User CRUD operations' business logic.
 */
@Service
public class UserService {

    /**
     * Constructor Dependency Injection
     */
    final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retrieves list of all Users from database with UserRepository's finaAll() method.
     * @return List of Users
     */
    public List<User> listAll(){
        return repository.findAll();
    }

    /**
     * Saves a User Object to the database with UserRepository's save() method. The password
     * associated with the User is encoded before saving.
     */
    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    /**
     * Retrieves a User object with specified id from database if exists with UserRepository's findById() method.
     * @param id
     * @return User object or null if not found
     */
    public User findUserById(Integer id){
        return repository.findById(id).orElse(null);
    }

    /**
     * Deletes User Object with specified id from database if exists with UserRepository's deleteById() method.
     * @param id
     * @return String message.
     */
    public String deleteUserById(Integer id) {

        repository.deleteById(id);
        return "User removed";
    }


    /**
     * Updates an existing User Object with new values with UserRepository's save() method.
     * @param user
     * @return Updated User Object
     */
    public User updateUser(User user){
        User existingUser = repository.findById(user.getId()).orElse(null);
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(existingUser);
    }
}
