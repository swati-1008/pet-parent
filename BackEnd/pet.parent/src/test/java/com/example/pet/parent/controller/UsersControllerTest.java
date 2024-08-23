package com.example.pet.parent.controller;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.request.Users.UserEditRequest;
import com.example.pet.parent.request.Users.UserIdRequest;
import com.example.pet.parent.request.Users.UserLoginRequest;
import com.example.pet.parent.request.Users.UsernameRequest;
import com.example.pet.parent.service.UsersService;
import com.example.pet.parent.util.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllUsers_ValidScenario() throws Exception {
        Users user1 = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");
        Users user2 = new Users(2, "user2", "user2@example.com", "password2",
                "img2", "bio2");
        List<Users> usersList = List.of(user1, user2);

        Mockito.when(usersService.getAllUsers()).thenReturn(usersList);

        mockMvc.perform(post("/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].username", is("user1")))
                .andExpect(jsonPath("$[1].userId", is(2)))
                .andExpect(jsonPath("$[1].username", is("user2")));

        Mockito.verify(usersService, Mockito.times(1)).getAllUsers();
    }

    @Test
    void testGetUserById_UserFound() throws Exception {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1);

        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersService.getUserById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(post("/user/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userIdRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.username", is("user1")));

        Mockito.verify(usersService, Mockito.times(1)).getUserById(1);
    }

    @Test
    void testGetUserById_UserNotFound() throws Exception {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1);

        Mockito.when(usersService.getUserById(1)).thenReturn(Optional.empty());

        mockMvc.perform(post("/user/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userIdRequest)))
                .andExpect(status().isNotFound());

        Mockito.verify(usersService, Mockito.times(1)).getUserById(1);
    }

    @Test
    void testGetUserByUsername_UserFound() throws Exception {
        UsernameRequest usernameRequest = new UsernameRequest();
        usernameRequest.setUsername("user1");

        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersService.findByUsername("user1")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/user/getByName")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(usernameRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.username", is("user1")));

        Mockito.verify(usersService, Mockito.times(1)).findByUsername("user1");
    }

    @Test
    void testGetUserByUsername_UserNotFound() throws Exception {
        UsernameRequest usernameRequest = new UsernameRequest();
        usernameRequest.setUsername("user1");

        Mockito.when(usersService.findByUsername("user1")).thenReturn(Optional.empty());

        mockMvc.perform(post("/user/getByName")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(usernameRequest)))
                .andExpect(status().isNotFound());

        Mockito.verify(usersService, Mockito.times(1)).findByUsername("user1");
    }

    @Test
    void testCreateUser_ValidScenario() throws Exception {
        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersService.createUser(Mockito.any(Users.class))).thenReturn(user);

        mockMvc.perform(post("/user/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.username", is("user1")));

        Mockito.verify(usersService, Mockito.times(1)).createUser(Mockito.any(Users.class));
    }

    @Test
    void testUpdateUser_ValidScenario() throws Exception {
        Users updatedUser = new Users(1, "user1_updated", "user1@example.com",
                "password1_updated", "img1", "bio1");

        UserEditRequest userEditRequest = new UserEditRequest();
        userEditRequest.setUserId(1);
        userEditRequest.setUsers(updatedUser);

        Mockito.when(usersService.updateUser(Mockito.eq(1), Mockito.any(Users.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/user/edit")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userEditRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("user1_updated")));

        Mockito.verify(usersService, Mockito.times(1))
                .updateUser(Mockito.eq(1), Mockito.any(Users.class));
    }

    @Test
    void testDeleteUser_ValidScenario() throws Exception {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1);

        Mockito.doNothing().when(usersService).deleteUser(1);

        mockMvc.perform(delete("/user/delete")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userIdRequest)))
                .andExpect(status().isOk());

        Mockito.verify(usersService, Mockito.times(1)).deleteUser(1);
    }

    @Test
    void testSignup_UsernameAlreadyTaken() throws Exception {
        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersService.findByUsername("user1")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/user/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username is already taken"));

        Mockito.verify(usersService, Mockito.times(1)).findByUsername("user1");
    }

    @Test
    void testSignUp_EmailAlreadyRegistered() throws Exception {
        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersService.findByEmail("user1@example.com")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/user/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email is already registered"));

        Mockito.verify(usersService, Mockito.times(1)).findByEmail("user1@example.com");
    }

    @Test
    void testSignUp_SuccessfulRegistration() throws Exception {
        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersService.findByUsername("user1")).thenReturn(Optional.empty());
        Mockito.when(usersService.findByEmail("user1@example.com")).thenReturn(Optional.empty());
        Mockito.when(usersService.createUser(Mockito.any(Users.class))).thenReturn(user);
        Mockito.when(passwordEncoder.encode(Mockito.any(CharSequence.class))).thenReturn("encodedPassword");

        mockMvc.perform(post("/user/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.username", is("user1")));

        Mockito.verify(usersService, Mockito.times(1)).createUser(Mockito.any(Users.class));
    }

    @Test
    void testLogin_SuccessfulLogin() throws Exception {
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("user1");
        userLoginRequest.setPassword("password1");

        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersService.findByUsername("user1")).thenReturn(Optional.of(user));
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(Mockito.mock(Authentication.class));
        Mockito.when(jwtTokenProvider.generateToken(Mockito.any(Authentication.class))).thenReturn("jwt-token");

        mockMvc.perform(post("/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userLoginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_token", is("jwt-token")));

        Mockito.verify(authenticationManager, Mockito.times(1))
                .authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        Mockito.verify(jwtTokenProvider, Mockito.times(1))
                .generateToken(Mockito.any(Authentication.class));
    }

    @Test
    void testLogin_InvalidCredentials() throws Exception {
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("user1");
        userLoginRequest.setPassword("wrongpassword");

        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        mockMvc.perform(post("/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userLoginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.['Authentication Exception']", is("Invalid credentials")));

        Mockito.verify(authenticationManager, Mockito.times(1))
                .authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void testSetIdToken_JWTTokenClass() throws Exception {
        String expectedToken = "newToken";
        JWTToken jwtToken = new JWTToken("initialToken");

        jwtToken.setIdToken(expectedToken);

        assertEquals(expectedToken, jwtToken.getIdToken(), "The idToken should be updated correctly");
    }

}
