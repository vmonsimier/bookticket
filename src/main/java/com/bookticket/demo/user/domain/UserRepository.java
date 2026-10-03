package com.bookticket.demo.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
    void delete(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
}