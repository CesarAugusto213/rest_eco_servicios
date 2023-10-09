package com.ecoservicios.rest_eco_servicios.controller;

import com.ecoservicios.rest_eco_servicios.models.Campus;
import com.ecoservicios.rest_eco_servicios.models.User;
import com.ecoservicios.rest_eco_servicios.repository.CampusRepository;
import com.ecoservicios.rest_eco_servicios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampusRepository campusRepository;

    @GetMapping
    public ResponseEntity<Page<User>> listUsers(Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        Optional<Campus> campusOptional = campusRepository.findById(user.getCampus().getId());

        if (!campusOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        user.setCampus(campusOptional.get());

        User saveUser = userRepository.save(user);
        URI path = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();

        return ResponseEntity.created(path).body(saveUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable String id) {
        Optional<Campus> campusOptional = campusRepository.findById(user.getCampus().getId());

        if (!campusOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        user.setCampus(campusOptional.get());
        user.setId(userOptional.get().getId());
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        userRepository.delete(userOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(userOptional.get());
    }

}
