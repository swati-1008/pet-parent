package com.example.pet.parent.service;

import com.example.pet.parent.model.Comment;
import com.example.pet.parent.model.Post;
import com.example.pet.parent.model.Users;
import com.example.pet.parent.repository.CommentRepository;
import com.example.pet.parent.repository.PostRepository;
import com.example.pet.parent.repository.UsersRepository;
import com.example.pet.parent.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void testGetCommentsByPostId_ValidPostId() {
        Post post = new Post();
        post.setPostId(1);

        Users user = new Users();
        user.setUserId(1);

        Comment comment = new Comment(1, "Great post", post, user);

        List<Comment> expectedComments = Collections.singletonList(comment);

        Mockito.when(commentRepository.findByPostPostId(1)).thenReturn(expectedComments);

        List<Comment> actualComments = commentService.getCommentsByPostId(1);

        assertEquals(expectedComments, actualComments);

        Mockito.verify(commentRepository, Mockito.times(1)).findByPostPostId(1);
    }

    @Test
    void testGetCommentsByPostId_InvalidPostId() {
        Mockito.when(commentRepository.findByPostPostId(999)).thenReturn(Collections.emptyList());

        List<Comment> actualComments = commentService.getCommentsByPostId(999);

        assertTrue(actualComments.isEmpty());

        Mockito.verify(commentRepository, Mockito.times(1)).findByPostPostId(999);
    }

    @Test
    void testGetCommentsByPostId_NoComments() {
        Mockito.when(commentRepository.findByPostPostId(1)).thenReturn(Collections.emptyList());

        List<Comment> actualComments = commentService.getCommentsByPostId(1);

        assertTrue(actualComments.isEmpty());

        Mockito.verify(commentRepository, Mockito.times(1)).findByPostPostId(1);
    }

    @Test
    void testAddComment_ValidData() {
        Post post = new Post();
        post.setPostId(1);

        Users user = new Users();
        user.setUserId(1);

        Comment comment = new Comment(1, "Nice post", post, user);

        Mockito.when(postRepository.findById(1)).thenReturn(Optional.of(post));
        Mockito.when(usersRepository.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        Comment savedComment = commentService.addComment(1, 1, "Nice post");

        assertNotNull(savedComment);
        assertEquals("Nice post", savedComment.getCommentText());

        Mockito.verify(postRepository, Mockito.times(1)).findById(1);
        Mockito.verify(usersRepository, Mockito.times(1)).findById(1);
        Mockito.verify(commentRepository, Mockito.times(1)).save(any(Comment.class));
    }

    @Test
    void testAddComment_InvalidPostId() {
        Mockito.when(postRepository.findById(999)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            commentService.addComment(999, 1, "Nice post");
        });

        assertEquals("Post not found", exception.getMessage());

        Mockito.verify(postRepository, Mockito.times(1)).findById(999);
        Mockito.verify(usersRepository, Mockito.times(0)).findById(anyInt());
        Mockito.verify(commentRepository, Mockito.times(0)).save(any(Comment.class));
    }

    @Test
    void testAddComment_InvalidUserId() {
        Post post = new Post();
        post.setPostId(1);

        Mockito.when(postRepository.findById(1)).thenReturn(Optional.of(post));
        Mockito.when(usersRepository.findById(999)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            commentService.addComment(1, 999, "Nice post");
        });

        assertEquals("User not found", exception.getMessage());

        Mockito.verify(postRepository, Mockito.times(1)).findById(1);
        Mockito.verify(usersRepository, Mockito.times(1)).findById(999);
        Mockito.verify(commentRepository, Mockito.times(0)).save(any(Comment.class));
    }

}
