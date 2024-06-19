package com.example.pet.parent.controller;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.request.Users.UserEditRequest;
import com.example.pet.parent.request.Users.UserIdRequest;
import com.example.pet.parent.request.Users.UserLoginRequest;
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

    @PostMapping("/all")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("/get")
    public Optional<Users> getUserById(@RequestBody UserIdRequest userGetRequest) {
        return usersService.getUserById(userGetRequest.getUserId());
    }

    @PostMapping("/create")
    public Users createUser(@RequestBody Users users) {
        return usersService.createUser(users);
    }

    @PutMapping("/edit")
    public Users updateUser(@RequestBody UserEditRequest userEditRequest) {
        return usersService.updateUser(userEditRequest.getUserId(), userEditRequest.getUsers());
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody UserIdRequest userIdRequest) {
        usersService.deleteUser(userIdRequest.getUserId());
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
    public ResponseEntity<?> login (@RequestBody UserLoginRequest userLoginRequest) {
        Optional<Users> optionalUser = usersService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        if (optionalUser.isPresent())
            return ResponseEntity.ok(optionalUser.get());
        else
            return ResponseEntity.status(401).body("Invalid Credentials");
    }
}
