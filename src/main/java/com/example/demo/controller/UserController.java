package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // GET /api/users - Liste tous les utilisateurs
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAll();
    }

    // GET /api/users/{id} - Récupère un utilisateur par ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST /api/users - Crée un nouvel utilisateur
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        User createdUser = service.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}