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

    public void saveUser(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        repository.saveAndFlush(user);
    }

    public User searchUserByEmail(String email) {
        return repository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
    }

    public void deleteUserByEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void updateUserById(UUID id, User user) {
        User userEntity = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Se trocar email, valida
        if (
            user.getEmail() != null &&
            !user.getEmail().equals(userEntity.getEmail()) &&
            repository.existsByEmail(user.getEmail())
        ) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        userEntity.setEmail(
            user.getEmail() != null ? user.getEmail() : userEntity.getEmail()
        );
        userEntity.setName(
            user.getName() != null ? user.getName() : userEntity.getName()
        );

        repository.saveAndFlush(userEntity);
    }
}
