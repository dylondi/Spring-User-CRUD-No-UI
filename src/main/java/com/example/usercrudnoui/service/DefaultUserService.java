package com.example.usercrudnoui.service;

import com.example.usercrudnoui.exceptions.ConstraintsViolationException;
import com.example.usercrudnoui.exceptions.EntityNotFoundException;
import com.example.usercrudnoui.model.User;
import com.example.usercrudnoui.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring Service Class containing User CRUD operations' business logic.
 */
@Service
public class DefaultUserService implements UserService{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);


    /**
     * Constructor Dependency Injection
     */
    final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retrieves list of all Users from database with UserRepository's finaAll() method.
     * @return List of Users
     */
    public List<User> listAll(){
        return userRepository.findAll();
    }

    /**
     * Saves a User Object to the database with UserRepository's save() method. The password
     * associated with the User is encoded before saving.
     */
    public User createUser(User user) throws ConstraintsViolationException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try{
            userRepository.save(user);
        }catch(DataIntegrityViolationException e){
            LOG.warn("ConstraintsViolationException while creating a driver: {}", user, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return userRepository.save(user);
    }

    /**
     * Retrieves a User object with specified id from database if exists with UserRepository's findById() method.
     * @param id
     * @return User object or null if not found
     */
    public User findUserById(Integer id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + id));
    }

    /**
     * Deletes User Object with specified id from database if exists with UserRepository's deleteById() method.
     * @param id
     * @return String message.
     */
    public String deleteUserById(Integer id) {

        userRepository.deleteById(id);

        return "User removed";
    }


    /**
     * Updates an existing User Object with new values with UserRepository's save() method.
     * @param user
     * @return Updated User Object
     */
    public User updateUser(User user) throws EntityNotFoundException {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + user.getId()));

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(existingUser);
    }
}
