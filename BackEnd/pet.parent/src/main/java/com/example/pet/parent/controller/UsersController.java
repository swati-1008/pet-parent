package com.example.pet.parent.controller;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/all")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/get")
    public Optional<Users> getUserById(@RequestParam(name = "id") int id) {
        return usersService.getUserById(id);
    }

    @PostMapping("/create")
    public Users createUser(@RequestBody Users users) {
        return usersService.createUser(users);
    }

    @PutMapping("/edit")
    public Users updateUser(@RequestParam(name = "id") int id, @RequestBody Users users) {
        return usersService.updateUser(id, users);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam(name = "id") int id) {
        usersService.deleteUser(id);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp (@RequestBody Users user) {
        Optional<Users> existingUser = usersService.findByUsername(user.getUsername());
        if (existingUser.isPresent())
            return ResponseEntity.badRequest().body("Username is already taken");
        existingUser = usersService.findByEmail(user.getEmail());
        if (existingUser.isPresent())
            return ResponseEntity.badRequest().body("Email is already registered");
        Users newUser = usersService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        Optional<Users> optionalUser = usersService.login(username, password);
        if (optionalUser.isPresent())
            return ResponseEntity.ok(optionalUser.get());
        else
            return ResponseEntity.status(401).body("Invalid Credentials");
    }
}
