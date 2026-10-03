package com.bookticket.demo.user.interfaces.dto;

public record LoginUserRequest(
    String email, 
    String password
) {}