package com.bookticket.demo.user.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookticket.demo.user.domain.User;
import com.bookticket.demo.user.domain.UserRepository;
import com.bookticket.demo.user.interfaces.dto.CreateUserRequest;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(CreateUserRequest user) {
        if(!user.password().equals(user.confirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        User userToSave = new User(user.firstName(), user.lastName(), user.email(), user.password());
        return userRepository.save(userToSave);
    }

    public void delete(CreateUserRequest user) {
        User userToDelete = new User(user.firstName(), user.lastName(), user.email(), user.password());
        userRepository.delete(userToDelete);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}