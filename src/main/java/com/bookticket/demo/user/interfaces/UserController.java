package com.bookticket.demo.user.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookticket.demo.user.application.UserService;
import com.bookticket.demo.user.domain.User;
import com.bookticket.demo.user.interfaces.dto.CreateUserRequest;
import com.bookticket.demo.user.interfaces.dto.LoginResponse;
import com.bookticket.demo.user.interfaces.dto.LoginUserRequest;

@RestController
@RequestMapping("/users")
public class UserController {
    final private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public LoginResponse login(LoginUserRequest user) {
        return userService.login(user);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User save(CreateUserRequest user) {
        return userService.save(user);
    }

    @DeleteMapping
    public void delete(CreateUserRequest user) {
        userService.delete(user);
    }
}