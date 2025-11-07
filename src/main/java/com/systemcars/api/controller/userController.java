package com.systemcars.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.systemcars.api.entities.User;
import com.systemcars.api.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class userController {

    private final UserRepository userRepository;

    public userController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserWithCars(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // O JPA carrega a lista de carros automaticamente (Lazy ou Eager depende do mapeamento)
        return ResponseEntity.ok(userOpt.get());
    }
}
