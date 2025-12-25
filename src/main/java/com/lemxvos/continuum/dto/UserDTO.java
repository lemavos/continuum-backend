package com.lemxvos.continuum.dto;

import com.lemxvos.continuum.entity.User;

public record UserDTO(long id, String name, String email) {

    public static UserDTO from(User user) {
        return new UserDTO(user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
