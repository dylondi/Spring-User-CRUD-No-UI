package com.example.usercrudnoui.service;

import com.example.usercrudnoui.exceptions.ConstraintsViolationException;
import com.example.usercrudnoui.exceptions.EntityNotFoundException;
import com.example.usercrudnoui.model.User;

import java.util.List;

public interface UserService {

    public List<User> listAll();

    public User createUser(User user) throws ConstraintsViolationException;

    public User findUserById(Integer id) throws EntityNotFoundException;

    public String deleteUserById(Integer id);

    public User updateUser(User user) throws EntityNotFoundException;
}
