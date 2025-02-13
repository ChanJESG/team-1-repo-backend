package com.example.rootsquad.backend.service;

import com.example.rootsquad.backend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {

    public User save(User user);
    public List<User> findAll();
    public User update (User user);
    public void delete (Long id);
    public Optional<User> findById(Long id);
    public Optional<User> findByEmail(String email);
}
