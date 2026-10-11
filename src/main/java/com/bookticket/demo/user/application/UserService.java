package com.bookticket.demo.user.application;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookticket.demo.shared.security.TokenProvider;
import com.bookticket.demo.user.domain.User;
import com.bookticket.demo.user.domain.UserRepository;
import com.bookticket.demo.user.interfaces.dto.CreateUserRequest;
import com.bookticket.demo.user.interfaces.dto.LoginResponse;
import com.bookticket.demo.user.interfaces.dto.LoginUserRequest;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public LoginResponse login(LoginUserRequest user) {
        Optional<User> userOptional = this.findByEmail(user.email());

        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Ressource not found");
        }

        if(passwordEncoder.matches(user.password(), userOptional.get().getPassword())) {
            String jwtToken = tokenProvider.generateToken(userOptional.get().getEmail(), userOptional.get().getRole());
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        Optional<User> userOptional = this.findByEmail(authentication.getName());
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Ressource not found");
        }
        if(!isAdmin && !Objects.equals(userOptional.get().getId(), user.getId())) {
            throw new AccessDeniedException("You are not allowed to delete this user");
        }
        userRepository.delete(user);
    }


    public Optional<User> findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        Optional<User> userOptional = this.findByEmail(authentication.getName());
        
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Ressource not found");
        }
        if(!isAdmin && !Objects.equals(userOptional.get().getId(), id)) {
            throw new AccessDeniedException("You are not allowed to view this user");
        }
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        if(!isAdmin) {
            throw new AccessDeniedException("You are not allowed to view all users");
        }
        return userRepository.findAll();
    }
}
