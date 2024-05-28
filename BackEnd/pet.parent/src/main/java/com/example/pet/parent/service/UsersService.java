package com.example.pet.parent.service;

import com.example.pet.parent.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<Users> getAllUsers();
    Optional<Users> getUserById(int id);
    Users createUser(Users users);
    Users updateUser(int id, Users users);
    void deleteUser(int id);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    Optional<Users> login (String username, String password);
}
