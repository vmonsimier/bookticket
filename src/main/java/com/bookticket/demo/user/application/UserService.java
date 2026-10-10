package com.bookticket.demo.user.application;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookticket.demo.user.domain.User;
import com.bookticket.demo.user.domain.UserRepository;
import com.bookticket.demo.user.infrastructure.JwtService;
import com.bookticket.demo.user.interfaces.dto.CreateUserRequest;
import com.bookticket.demo.user.interfaces.dto.LoginResponse;
import com.bookticket.demo.user.interfaces.dto.LoginUserRequest;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginUserRequest user) {
        User userFound = userRepository.findByEmail(user.email())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(passwordEncoder.matches(user.password(), userFound.getPassword())) {
            String jwtToken = jwtService.generateToken(userFound.getEmail(), userFound.getRole());
            return new LoginResponse(jwtToken);
        } else {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }


    public User save(CreateUserRequest user) {
        if(!user.password().equals(user.confirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        User userToSave = new User(user.firstName(), user.lastName(), user.email(), passwordEncoder.encode(user.password()));
        return userRepository.save(userToSave);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}