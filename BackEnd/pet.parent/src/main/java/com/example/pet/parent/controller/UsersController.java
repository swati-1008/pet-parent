package com.example.pet.parent.controller;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
}
