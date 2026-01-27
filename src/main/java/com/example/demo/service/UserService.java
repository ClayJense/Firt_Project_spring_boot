package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id) {
        // ".orElseThrow" = si pas trouvé, lance une exception
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + id));
    }

    public User create(UserDTO userDTO) {
        // 1. Vérifier si l'email existe déjà
        if (repository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email déjà utilisé : " + userDTO.getEmail());
        }

        // 2. Convertir DTO → Entity
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        // 3. Sauvegarder
        return repository.save(user);
    }
}