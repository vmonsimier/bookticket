package com.bookticket.demo.user.application;

import com.bookticket.demo.user.domain.Role;

public interface TokenProvider {
    String generateToken(String subject, Role role);
}
