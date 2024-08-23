package com.example.pet.parent.service;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.repository.UsersRepository;
import com.example.pet.parent.service.impl.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void testLoadUserByUsername_UserFound() {
        String username = "testUser";
        Users user = new Users(1, "testUser", "testUser@example.com", "password123",
                "img", "bio");

        Mockito.when(usersRepository.findByUsername(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUsername(username);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        String username = "nonExistentUser";

        Mockito.when(usersRepository.findByUsername(username)).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () ->
                customUserDetailsService.loadUserByUsername(username));

        assertEquals("User not found with username: " + username, exception.getMessage());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUsername(username);
    }

    @Test
    void testLoadUserByUsername_NullUsername() {
        String username = null;

        assertThrows(IllegalArgumentException.class, () ->
                customUserDetailsService.loadUserByUsername(username));
    }

    @Test
    void testLoadUserByUsername_EmptyUsername() {
        String username = "";

        assertThrows(UsernameNotFoundException.class, () ->
                customUserDetailsService.loadUserByUsername(username));

        Mockito.verify(usersRepository, Mockito.times(1)).findByUsername(username);
    }

    @Test
    void testLoadUserByUsername_MultipleUsersFound() {
        String username = "testUser";
        Users user1 = new Users(1, "testUser", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersRepository.findByUsername(username)).thenReturn(Optional.of(user1));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals("password1", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUsername(username);
    }

    @Test
    void testLoadUserByUsername_NoAuthorities() {
        String username = "testUser";
        Users user1 = new Users(1, "testUser", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersRepository.findByUsername(username)).thenReturn(Optional.of(user1));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().isEmpty());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUsername(username);
    }

}
