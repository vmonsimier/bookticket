package com.bookticket.demo.user.interfaces.dto;

import java.time.LocalDateTime;

public record UserResponse(
    Long id, 
    String firstName, 
    String lastName, 
    String email,
    LocalDateTime createdAt
) {}