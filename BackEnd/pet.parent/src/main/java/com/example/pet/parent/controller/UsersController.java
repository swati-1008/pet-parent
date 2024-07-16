package com.example.pet.parent.controller;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.request.Users.UserEditRequest;
import com.example.pet.parent.request.Users.UserIdRequest;
import com.example.pet.parent.request.Users.UserLoginRequest;
import com.example.pet.parent.service.UsersService;
import com.example.pet.parent.util.JwtTokenProvider;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/*
    @RestController indicating that this class handles HTTP requests and
    returns JSON or XML responses
*/
@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users newUser = usersService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody UserLoginRequest userLoginRequest) {
        try {
            /*
                new UsernamePasswordAuthenticationToken creates an authentication token with the given username and password
                 authenticationManager then authenticates the user based on the provided username and password
             */
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            Optional<Users> optionalUser = usersService.findByUsername(userLoginRequest.getUsername());
            return ResponseEntity.ok(new JWTToken(token));
        } catch (AuthenticationException exception) {
            return new ResponseEntity<>(Collections.singletonMap("Authentication Exception", exception.getLocalizedMessage()),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}

class JWTToken {

    private String idToken;

    JWTToken (String idToken) {
        this.idToken = idToken;
    }

    @JsonProperty("id_token")                   // shows that the key of JSON pair will be id_token
    String getIdToken() {
        return idToken;
    }

    void setIdToken(String idToken) {
        this.idToken = idToken;
    }

}