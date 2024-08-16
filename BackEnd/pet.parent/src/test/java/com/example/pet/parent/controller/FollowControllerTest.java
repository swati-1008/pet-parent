package com.example.pet.parent.controller;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.request.Follow.FollowIdRequest;
import com.example.pet.parent.service.FollowService;
import com.example.pet.parent.service.UsersService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FollowControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FollowService followService;

    @Mock
    private UsersService usersService;

    @InjectMocks
    private FollowController followController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(followController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetPeopleYouMayKnow_ValidData() throws Exception {
        Users user1 = new Users(1, "User1", "email1", "password1",
                "img1", "bio1");
        Users user2 = new Users(2, "User2", "email2", "password2",
                "img2", "bio2");
        List<Users> usersList = Arrays.asList(user1, user2);

        Mockito.when(usersService.getPeopleYouMayKnow(1)).thenReturn(usersList);

        FollowIdRequest followIdRequest = new FollowIdRequest();
        followIdRequest.setUserId(1);

        mockMvc.perform(post("/follow/suggestions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(followIdRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].username").value("User1"))
                .andExpect(jsonPath("$[1].userId").value(2))
                .andExpect(jsonPath("$[1].username").value("User2"));
    }

    @Test
    void testGetPeopleYouMayKnow_InvalidUserId() throws Exception {
        Mockito.when(usersService.getPeopleYouMayKnow(999)).thenReturn(null);

        FollowIdRequest followIdRequest = new FollowIdRequest();
        followIdRequest.setUserId(999);

        mockMvc.perform(post("/follow/suggestions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(followIdRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPeopleYouMayKnow_MissingUserId() throws Exception {
        FollowIdRequest followIdRequest = new FollowIdRequest();

        mockMvc.perform(post("/follow/suggestions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(followIdRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetPeopleYouMayKnow_NoUsersFound() throws Exception {
        Mockito.when(usersService.getPeopleYouMayKnow(1)).thenReturn(List.of());

        FollowIdRequest followIdRequest = new FollowIdRequest();
        followIdRequest.setUserId(1);

        mockMvc.perform(post("/follow/suggestions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(followIdRequest)))
                .andExpect(status().isNotFound());

        Mockito.verify(usersService, Mockito.times(1)).getPeopleYouMayKnow(1);
    }


}