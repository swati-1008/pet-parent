package com.example.pet.parent.service.impl;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.repository.FollowRepository;
import com.example.pet.parent.repository.UsersRepository;
import com.example.pet.parent.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl (UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private FollowRepository followRepository;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    @Override
    public Users updateUser(int id, Users user) {
        Optional<Users> currentUser = usersRepository.findById(id);
        if (currentUser.isPresent()) {
            Users tempUser = currentUser.get();
            tempUser.setUsername(user.getUsername());
            tempUser.setEmail(user.getEmail());
            if (!Objects.equals(tempUser.getPassword(), user.getPassword()))
                tempUser.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public Optional<Users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public Optional<Users> login(String username, String password) {
        Optional<Users> optionalUser = usersRepository.findByUsername((username));
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword()))
                return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public List<Users> getPeopleYouMayKnow (int userId) {
        List<Integer> suggestedUserIds = followRepository.findPeopleYouMayKnow(userId);
        return usersRepository.findAllById(suggestedUserIds);
    }
}