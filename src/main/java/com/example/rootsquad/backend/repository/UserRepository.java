package com.example.rootsquad.backend.repository;

import com.example.rootsquad.backend.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;


public interface UserRepository extends ListCrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
