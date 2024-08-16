package com.example.pet.parent.controller;

import com.example.pet.parent.model.Comment;
import com.example.pet.parent.model.Post;
import com.example.pet.parent.model.Users;
import com.example.pet.parent.request.Comment.AddCommentRequest;
import com.example.pet.parent.request.Comment.CommentRequest;
import com.example.pet.parent.service.CommentService;
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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetCommentsByPost_ValidPostId() throws Exception {
        Post post = new Post(1, new Users(1, "User1", "email1", "password1",
                "img1", "bio1"), "Content1", "postImg1",
                LocalDateTime.of(2024, 7, 22, 14, 26, 52),
                10L, 5L, 2L);
        Users user = new Users(1, "User1", "email1", "password1",
                "img1", "bio1");
        Comment comment = new Comment(1, "Great post!", post, user);
        List<Comment> comments = Collections.singletonList(comment);
        Mockito.when(commentService.getCommentsByPostId(1)).thenReturn(comments);

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(1);

        mockMvc.perform(post("/comments/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].commentText", is("Great post!")));

        Mockito.verify(commentService, Mockito.times(1)).getCommentsByPostId(1);
    }

    @Test
    void testGetCommentsByPost_InvalidPostId() throws Exception {
        Mockito.when(commentService.getCommentsByPostId(999)).thenReturn(Collections.emptyList());

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(999);

        mockMvc.perform(post("/comments/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        Mockito.verify(commentService, Mockito.times(1)).getCommentsByPostId(999);
    }

    @Test
    void testGetCommentsByPost_NoComments() throws Exception {
        Mockito.when(commentService.getCommentsByPostId(1)).thenReturn(Collections.emptyList());

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(1);

        mockMvc.perform(post("/comments/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        Mockito.verify(commentService, Mockito.times(1)).getCommentsByPostId(1);
    }

    @Test
    void testAddComment_ValidData() throws Exception {
        Post post = new Post(1, new Users(1, "User1", "email1", "password1",
                "img1", "bio1"), "Content1", "postImg1",
                LocalDateTime.of(2024, 7, 22, 14, 26, 52),
                10L, 5L, 2L);
        Users user = new Users(1, "User1", "email1", "password1",
                "img1", "bio1");
        Comment comment = new Comment(1, "Nice post!", post, user);

        Mockito.doReturn(comment).when(commentService).addComment(1, 1, "Nice post!");

        AddCommentRequest addCommentRequest = new AddCommentRequest();
        addCommentRequest.setPostId(1);
        addCommentRequest.setUserId(1);
        addCommentRequest.setCommentText("Nice post!");

        mockMvc.perform(post("/comments/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(addCommentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentText", is("Nice post!")));

        Mockito.verify(commentService, Mockito.times(1))
                .addComment(1, 1, "Nice post!");
    }

    @Test
    void testAddComment_InvalidPostId() throws Exception {
        Mockito.when(commentService.addComment(999, 1, "Nice post!"))
                .thenThrow(new IllegalArgumentException("Invalid post ID"));

        AddCommentRequest addCommentRequest = new AddCommentRequest();
        addCommentRequest.setPostId(999);
        addCommentRequest.setUserId(1);
        addCommentRequest.setCommentText("Nice post!");

        mockMvc.perform(post("/comments/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(addCommentRequest)))
                .andExpect(status().isBadRequest());

        Mockito.verify(commentService, Mockito.times(1))
                .addComment(999, 1, "Nice post!");
    }

    @Test
    void testAddComment_InvalidUserId() throws Exception {
        Mockito.when(commentService.addComment(1, 999, "Nice post!"))
                .thenThrow(new IllegalArgumentException("Invalid user ID"));

        AddCommentRequest addCommentRequest = new AddCommentRequest();
        addCommentRequest.setPostId(1);
        addCommentRequest.setUserId(999);
        addCommentRequest.setCommentText("Nice post!");

        mockMvc.perform(post("/comments/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(addCommentRequest)))
                .andExpect(status().isBadRequest());

        Mockito.verify(commentService, Mockito.times(1))
                .addComment(1, 999, "Nice post!");
    }

    @Test
    void testAddComment_EmptyCommentText() throws Exception {
        AddCommentRequest addCommentRequest = new AddCommentRequest();
        addCommentRequest.setPostId(1);
        addCommentRequest.setUserId(1);
        addCommentRequest.setCommentText("");

        mockMvc.perform(post("/comments/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(addCommentRequest)))
                .andExpect(status().isBadRequest());

        Mockito.verify(commentService, Mockito.times(0))
                .addComment(anyInt(), anyInt(), anyString());
    }

    @Test
    void testAddComment_MissingFields() throws Exception {
        AddCommentRequest addCommentRequest = new AddCommentRequest();
        addCommentRequest.setPostId(1);

        mockMvc.perform(post("/comments/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(addCommentRequest)))
                .andExpect(status().isBadRequest());

        Mockito.verify(commentService, Mockito.times(0))
                .addComment(anyInt(), anyInt(), anyString());
    }

}