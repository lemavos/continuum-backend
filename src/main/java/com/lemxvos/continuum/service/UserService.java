package com.lemxvos.continuum.service;

import com.lemxvos.continuum.entity.User;
import com.lemxvos.continuum.repository.UserRepository;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void create(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already registred");
        }

        repository.saveAndFlush(user);
    }

    public User read(UUID id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void update(UUID id, User user) {
        User userEntity = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Se trocar email, valida
        if (user.getEmail() != null
                && !user.getEmail().equals(userEntity.getEmail())
                && repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already registred");
        }

        
        userEntity.setEmail(
                user.getEmail() != null ? user.getEmail() : userEntity.getEmail()
        );
        userEntity.setName(
                user.getName() != null ? user.getName() : userEntity.getName()
        );

        repository.saveAndFlush(userEntity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
