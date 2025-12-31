
package com.lemxvos.continuum.controller;

import com.lemxvos.continuum.dto.UserDTO;
import com.lemxvos.continuum.entity.User;
import com.lemxvos.continuum.service.UserService;
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
    public ResponseEntity<String> create(@RequestBody User user) {
        String response = userService.create(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<UserDTO> read(
            @RequestParam long id
    ) {
        return ResponseEntity.ok(userService.read(id));
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestParam long id,
            @RequestBody User user) {
        String response = userService.update(id, user);
        return ResponseEntity.ok(response);   
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @RequestParam long id
    ) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}