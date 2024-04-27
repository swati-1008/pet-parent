package com.example.pet.parent.service.impl;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.repository.UsersRepository;
import com.example.pet.parent.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl (UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(int id) {
        return usersRepository.findById(id);
    }

    @Override
    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public Users updateUser(int id, Users user) {
        Optional<Users> currentUser = usersRepository.findById(id);
        if (currentUser.isPresent()) {
            Users tempUser = currentUser.get();
            tempUser.setUsername(user.getUsername());
            tempUser.setEmail(user.getEmail());
            tempUser.setPassword(user.getPassword());
            return usersRepository.save(tempUser);
        }
        else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Override
    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }
}
