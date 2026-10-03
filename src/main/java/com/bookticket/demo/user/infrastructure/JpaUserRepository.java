package com.bookticket.demo.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookticket.demo.user.domain.User;


public interface JpaUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}