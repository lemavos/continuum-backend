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
        repository.saveAndFlush(user);
    }

    public User searchUserByEmail(String email) {
        return repository
            .findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Email not found!"));
    }

    public void deleteUserByEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void updateUserByEmail(UUID id, User user) {
        User userEntity = repository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("User not found!"));

        User updatedUser = User.builder()
            .email(
                user.getEmail() != null
                    ? user.getEmail()
                    : userEntity.getEmail()
            )
            .name(
                user.getName() != null ? user.getName() : userEntity.getName()
            )
            .id(userEntity.getId())
            .build();

        repository.saveAndFlush(updatedUser);
    }
}
