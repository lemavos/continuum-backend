package com.lemxvos.continuum.controller;

import com.lemxvos.continuum.entity.User;
import com.lemxvos.continuum.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody User user) {
        userService.create(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<User> read(
        @RequestParam UUID id
    ) {
        return ResponseEntity.ok(userService.read(id));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestParam UUID id, 
                                        @RequestBody User user) {
        userService.update(id, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
        @RequestParam UUID id
    ) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
