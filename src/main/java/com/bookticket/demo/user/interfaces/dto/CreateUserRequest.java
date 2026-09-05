package com.bookticket.demo.user.interfaces.dto;

public record CreateUserRequest(
    String firstName, 
    String lastName, 
    String email, 
    String password,
    String confirmPassword
) {}