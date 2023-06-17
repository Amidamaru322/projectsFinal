package com.example.projects.controller;

import com.example.projects.Exception.NotFoundException;
import com.example.projects.model.UserProfile;
import com.example.projects.model.Users;
import com.example.projects.repository.UserRepository;
import com.example.projects.request.PasswordUpdateRequest;
import com.example.projects.request.UserProfileUpdateRequest;
import com.example.projects.request.UserRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody UserRegistrationRequest request) {
        Users newUser = new Users(request.getUsername(), request.getEmail(), request.getPassword());
        newUser.setUsername(request.getUsername()); // Установите имя пользователя
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());

        userRepository.save(newUser);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        List<Users> userList = userRepository.findAll();
        List<UserProfile> userProfiles = new ArrayList<>();

        for (Users user : userList) {
            UserProfile userProfile = new UserProfile(user.getUsername(), user.getEmail());
            userProfiles.add(userProfile);
        }

        return ResponseEntity.ok(userProfiles);
    }


    @GetMapping("/profile")
    public ResponseEntity<UserProfile> getUserProfile() {
        Long userId = getUserId();
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        UserProfile userProfile = new UserProfile(user.getUsername(), user.getEmail());
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfileUpdateRequest request) {
        Long userId = getUserId();
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        userRepository.save(user);

        UserProfile userProfile = new UserProfile(user.getUsername(), user.getEmail());
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestBody PasswordUpdateRequest request) {
        Long userId = getUserId();
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setPassword(request.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profile")
    public ResponseEntity<Void> deleteUserProfile() {
        Long userId = getUserId();
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found");
        }

        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    private Long getUserId() {
        return 1L;
    }
}

