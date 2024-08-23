package com.example.pet.parent.service;

import com.example.pet.parent.dto.PostDTO;
import com.example.pet.parent.model.*;
import com.example.pet.parent.repository.PostLikesRepository;
import com.example.pet.parent.repository.PostRepository;
import com.example.pet.parent.repository.PostSavesRepository;
import com.example.pet.parent.repository.UsersRepository;
import com.example.pet.parent.service.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostLikesRepository postLikesRepository;

    @Mock
    private PostSavesRepository postSavesRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private PostServiceImpl postService;

    private Post post1;
    private Post post2;
    private Users user;

    @BeforeEach
    void setup() {
        post1 = new Post(1, new Users(1, "User1", "email1", "password1",
                "img1", "bio1"), "Content1", "postImg1",
                LocalDateTime.of(2024, 7, 22, 14, 26, 52),
                10L, 5L, 2L);
        post2 = new Post(2, new Users(2, "User2", "email2", "password2",
                "img2", "bio2"), "Content2", "postImg2",
                LocalDateTime.of(2024, 5, 13, 7, 23, 45),
                5L, 2L, 7L);
        user = new Users(1, "User1", "email1", "password1",
                "img1", "bio1");
    }

    @Test
    void testGetAllPosts() {
        List<Post> posts = List.of(post1, post2);

        Mockito.when(postRepository.findAll()).thenReturn(posts);
        Mockito.when(postRepository.countLikesByPostId(post1.getPostId())).thenReturn(10L);
        Mockito.when(postRepository.countCommentsByPostId(post1.getPostId())).thenReturn(5L);
        Mockito.when(postRepository.countSavesByPostId(post1.getPostId())).thenReturn(2L);

        List<Post> fetchedPosts = postService.getAllPosts();

        assertEquals(2, fetchedPosts.size());
        assertEquals(10L, fetchedPosts.get(0).getLikeCount());
        assertEquals(5L, fetchedPosts.get(0).getCommentCount());
        assertEquals(2L, fetchedPosts.get(0).getSavesCount());

        Mockito.verify(postRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetAllPostsWithPagination() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Post> postPage = new PageImpl<>(List.of(post1, post2), pageable, 2);

        Mockito.when(postRepository.findAll(pageable)).thenReturn(postPage);
        Mockito.when(postRepository.countLikesByPostId(anyInt())).thenReturn(10L);
        Mockito.when(postRepository.countCommentsByPostId(anyInt())).thenReturn(5L);
        Mockito.when(postRepository.countSavesByPostId(anyInt())).thenReturn(2L);

        Page<PostDTO> fetchedPostDTOs = postService.getAllPosts(pageable);

        assertEquals(2, fetchedPostDTOs.getTotalElements());
        assertEquals(10L, fetchedPostDTOs.getContent().get(0).getLikeCount());

        Mockito.verify(postRepository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    void testGetPostById_ExistingPost() {
        Mockito.when(postRepository.findById(post1.getPostId())).thenReturn(Optional.of(post1));
        Mockito.when(postRepository.countLikesByPostId(post1.getPostId())).thenReturn(10L);
        Mockito.when(postRepository.countCommentsByPostId(post1.getPostId())).thenReturn(5L);
        Mockito.when(postRepository.countSavesByPostId(post1.getPostId())).thenReturn(2L);

        Optional<Post> fetchedPost = postService.getPostById(post1.getPostId());

        assertTrue(fetchedPost.isPresent());
        assertEquals(10L, fetchedPost.get().getLikeCount());

        Mockito.verify(postRepository, Mockito.times(1)).findById(post1.getPostId());
    }

    @Test
    void testGetPostById_NonExistingPost() {
        Mockito.when(postRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Post> fetchedPost = postService.getPostById(999);

        assertFalse(fetchedPost.isPresent());

        Mockito.verify(postRepository, Mockito.times(1)).findById(999);
    }

    @Test
    void testCreatePost() {
        Mockito.when(postRepository.save(any(Post.class))).thenReturn(post1);

        Post createdPost = postService.createPost(post1);

        assertNotNull(createdPost);
        assertEquals(post1.getContent(), createdPost.getContent());

        Mockito.verify(postRepository, Mockito.times(1)).save(post1);
    }

    @Test
    void testUpdatePost_ExistingPost() {
        Mockito.when(postRepository.findById(post1.getPostId())).thenReturn(Optional.of(post1));
        Mockito.when(postRepository.save(post1)).thenReturn(post1);

        Post updatedPost = postService.updatePost(post1.getPostId(), post1);

        assertNotNull(updatedPost);
        assertEquals(post1.getContent(), updatedPost.getContent());

        Mockito.verify(postRepository, Mockito.times(1)).findById(post1.getPostId());
        Mockito.verify(postRepository, Mockito.times(1)).save(post1);
    }

    @Test
    void testUpdatePost_NonExistingPost() {
        Mockito.when(postRepository.findById(999)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            postService.updatePost(999, post1);
        });

        assertEquals("Post not found with id: 999", exception.getMessage());

        Mockito.verify(postRepository, Mockito.times(1)).findById(999);
    }

    @Test
    void testUpdatePost_WhenUserIsNull_ShouldNotUpdateUsers() {
        Post updatedPost = new Post(3, null, "Updated Content", "postImg1",
                LocalDateTime.of(2024, 7, 22, 14, 26, 52),
                10L, 5L, 2L);

        Mockito.when(postRepository.findById(post1.getPostId())).thenReturn(Optional.of(post1));
        Mockito.when(postRepository.save(any(Post.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Post result = postService.updatePost(post1.getPostId(), updatedPost);

        assertEquals("Updated Content", result.getContent());
        assertEquals(1, result.getUsers().getUserId());
        assertEquals("User1", result.getUsers().getUsername());

        Mockito.verify(postRepository, Mockito.times(1)).findById(post1.getPostId());
        Mockito.verify(postRepository, Mockito.times(1)).save(any(Post.class));
    }


    @Test
    void testDeletePost() {
        Mockito.doNothing().when(postRepository).deleteById(post1.getPostId());

        postService.deletePost(post1.getPostId());

        Mockito.verify(postRepository, Mockito.times(1)).deleteById(post1.getPostId());
    }

    @Test
    void testLikePost() {
        Mockito.when(postRepository.findById(post1.getPostId())).thenReturn(Optional.of(post1));
        Mockito.when(usersRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        postService.likePost(post1.getPostId(), user.getUserId());

        Mockito.verify(postLikesRepository, Mockito.times(1)).save(any(PostLikes.class));
    }

    @Test
    void testUnlikePost() {
        Mockito.when(postRepository.findById(post1.getPostId())).thenReturn(Optional.of(post1));
        Mockito.when(usersRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        postService.unlikePost(post1.getPostId(), user.getUserId());

        Mockito.verify(postLikesRepository, Mockito.times(1)).deleteById(any(PostLikeId.class));
    }

    @Test
    void testSavePost() {
        Mockito.when(postRepository.findById(post1.getPostId())).thenReturn(Optional.of(post1));
        Mockito.when(usersRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        postService.savePost(post1.getPostId(), user.getUserId());

        Mockito.verify(postSavesRepository, Mockito.times(1)).save(any(PostSaves.class));
    }

    @Test
    void testUnsavePost() {
        Mockito.when(postRepository.findById(post1.getPostId())).thenReturn(Optional.of(post1));
        Mockito.when(usersRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        postService.unsavePost(post1.getPostId(), user.getUserId());

        Mockito.verify(postSavesRepository, Mockito.times(1)).deleteById(any(PostSavesId.class));
    }

    @Test
    void testGetLikedPostsByUser() {
        Mockito.when(postLikesRepository.findByUserUserId(user.getUserId()))
                .thenReturn(List.of(new PostLikes(user, post1), new PostLikes(user, post2)));

        List<Post> likedPosts = postService.getLikedPostsByUser(user.getUserId());

        assertEquals(2, likedPosts.size());

        Mockito.verify(postLikesRepository, Mockito.times(1)).findByUserUserId(user.getUserId());
    }

    @Test
    void testGetSavedPostsByUser() {
        Mockito.when(postSavesRepository.findByUserUserId(user.getUserId()))
                .thenReturn(List.of(new PostSaves(user, post1), new PostSaves(user, post2)));

        List<Post> savedPosts = postService.getSavedPostsByUser(user.getUserId());

        assertEquals(2, savedPosts.size());

        Mockito.verify(postSavesRepository, Mockito.times(1)).findByUserUserId(user.getUserId());
    }
}
