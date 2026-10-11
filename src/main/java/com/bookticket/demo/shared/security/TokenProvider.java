package com.bookticket.demo.shared.security;

import com.bookticket.demo.user.domain.Role;

public interface TokenProvider {
    String generateToken(String subject, Role role);
}
