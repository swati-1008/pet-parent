package com.example.pet.parent.service;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.repository.FollowRepository;
import com.example.pet.parent.repository.UsersRepository;
import com.example.pet.parent.service.impl.UsersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private FollowRepository followRepository;

    @InjectMocks
    private UsersServiceImpl usersService;

    @Test
    void testGetAllUsers_ReturnsListOfUsers() {
        Users user1 = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");
        Users user2 = new Users(2, "user2", "user2@example.com", "password2",
                "img2", "bio2");
        List<Users> usersList = List.of(user1, user2);

        Mockito.when(usersRepository.findAll()).thenReturn(usersList);

        List<Users> result = usersService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());

        Mockito.verify(usersRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetUserById_UserFound() {
        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<Users> result = usersService.getUserById(1);

        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getUsername());

        Mockito.verify(usersRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void testGetUserById_UserNotFound() {
        Mockito.when(usersRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Users> result = usersService.getUserById(1);

        assertFalse(result.isPresent());

        Mockito.verify(usersRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void testCreateUser_UserCreated() {
        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersRepository.save(user)).thenReturn(user);

        Users result = usersService.createUser(user);

        assertEquals("user1", result.getUsername());

        Mockito.verify(usersRepository, Mockito.times(1)).save(user);
    }

    @Test
    void testUpdateUser_UserFoundAndUpdated() {
        Users existingUser = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");
        Users updatedUser = new Users(1, "newUser", "newUser@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersRepository.findById(1)).thenReturn(Optional.of(existingUser));
        Mockito.when(usersRepository.save(existingUser)).thenReturn(existingUser);

        Users result = usersService.updateUser(1, updatedUser);

        assertEquals("newUser", result.getUsername());
        assertEquals("newUser@example.com", result.getEmail());

        Mockito.verify(usersRepository, Mockito.times(1)).findById(1);
        Mockito.verify(usersRepository, Mockito.times(1)).save(existingUser);
    }

    @Test
    void testUpdateUser_UserNotFound() {
        Users updatedUser = new Users(1, "newUser", "newUser@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usersService.updateUser(1, updatedUser);
        });

        assertEquals("User not found with id: 1", exception.getMessage());

        Mockito.verify(usersRepository, Mockito.times(1)).findById(1);
        Mockito.verify(usersRepository, Mockito.never()).save(Mockito.any(Users.class));
    }

    @Test
    void testDeleteUser_UserDeletedSuccessfully() {
        usersService.deleteUser(1);

        Mockito.verify(usersRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    void testFindByUsername_UserFound() {
        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersRepository.findByUsername("user1")).thenReturn(Optional.of(user));

        Optional<Users> result = usersService.findByUsername("user1");

        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getUsername());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUsername("user1");
    }

    @Test
    void testFindByUsername_UserNotFound() {
        Mockito.when(usersRepository.findByUsername("user1")).thenReturn(Optional.empty());

        Optional<Users> result = usersService.findByUsername("user1");

        assertFalse(result.isPresent());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUsername("user1");
    }

    @Test
    void testFindByEmail_UserFound() {
        String email = "user@example.com";
        Users user = new Users(1, "user1", "user@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<Users> result = usersService.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        assertEquals(email, result.get().getEmail());

        Mockito.verify(usersRepository, Mockito.times(1)).findByEmail(email);
    }

    @Test
    void testFindByEmail_UserNotFound() {
        String email = "nonexistent@example.com";

        Mockito.when(usersRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<Users> result = usersService.findByEmail(email);

        assertTrue(result.isEmpty());

        Mockito.verify(usersRepository, Mockito.times(1)).findByEmail(email);
    }

    @Test
    void testLogin_SuccessfulLogin() {
        Users user = new Users(1, "user1", "user1@example.com", "password1",
                "img1", "bio1");

        Mockito.when(usersRepository.findByUsername("user1")).thenReturn(Optional.of(user));

        Optional<Users> result = usersService.login("user1", "password1");

        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getUsername());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUsername("user1");
    }

    @Test
    void testLogin_UserNotFound() {
        String username = "nonexistentUser";
        String password = "password";

        Mockito.when(usersRepository.findByUsername(username)).thenReturn(Optional.empty());

        Optional<Users> result = usersService.login(username, password);

        assertTrue(result.isEmpty());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUsername(username);
    }

    @Test
    void testGetPeopleYouMayKnow_ReturnsSuggestedUsers() {
        Users user2 = new Users(2, "user2", "user2@example.com", "password2",
                "img2", "bio2");
        Users user3 = new Users(3, "user3", "user3@example.com", "password3",
                "img3", "bio3");

        List<Integer> suggestedUserIds = List.of(2, 3);
        List<Users> suggestedUsers = List.of(user2, user3);

        Mockito.when(followRepository.findPeopleYouMayKnow(1)).thenReturn(suggestedUserIds);
        Mockito.when(usersRepository.findAllById(suggestedUserIds)).thenReturn(suggestedUsers);

        List<Users> result = usersService.getPeopleYouMayKnow(1);

        assertEquals(2, result.size());
        assertEquals("user2", result.get(0).getUsername());

        Mockito.verify(followRepository, Mockito.times(1)).findPeopleYouMayKnow(1);
        Mockito.verify(usersRepository, Mockito.times(1)).findAllById(suggestedUserIds);
    }

}
