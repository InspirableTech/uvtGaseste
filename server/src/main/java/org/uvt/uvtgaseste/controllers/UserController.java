package org.uvt.uvtgaseste.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uvt.uvtgaseste.models.UserDTO;
import org.uvt.uvtgaseste.services.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    private ResponseEntity<List<UserDTO>> getAllUsers () {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    private ResponseEntity<UserDTO> getUserById (Long userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @PostMapping
    private ResponseEntity<UserDTO> createUser (UserDTO userDTO) {
        return ResponseEntity.ok(this.userService.createUser(userDTO));
    }

    @PutMapping("/{userId}")
    private ResponseEntity<UserDTO> updateUser (UserDTO userDTO, Long userId) {
        return ResponseEntity.ok(this.userService.updateUser(userDTO, userId));
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<Boolean> deleteUser (Long id) {
        return ResponseEntity.ok(this.userService.deleteUSer(id));
    }
}
