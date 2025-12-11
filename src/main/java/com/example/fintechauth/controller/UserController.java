package com.example.fintechauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.fintechauth.entity.User;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(new UserInfo(
                user.getFullName(),
                user.getEmail(),
                user.getRole().name()
        ));
    }

    private static class UserInfo {
        private final String fullName;
        private final String email;
        private final String role;

        public UserInfo(String fullName, String email, String role) {
            this.fullName = fullName;
            this.email = email;
            this.role = role;
        }

        public String getFullName() {
            return fullName;
        }

        public String getEmail() {
            return email;
        }

        public String getRole() {
            return role;
        }
    }
}
