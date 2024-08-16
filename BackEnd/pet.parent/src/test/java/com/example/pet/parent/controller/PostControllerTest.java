package com.example.pet.parent.controller;

import com.example.pet.parent.dto.PostDTO;
import com.example.pet.parent.dto.UserDTO;
import com.example.pet.parent.model.Post;
import com.example.pet.parent.model.Users;
import com.example.pet.parent.request.Post.ActionRequest;
import com.example.pet.parent.request.Post.PostPageRequest;
import com.example.pet.parent.request.Post.PostEditRequest;
import com.example.pet.parent.request.Post.PostIdRequest;
import com.example.pet.parent.request.Users.UserIdRequest;
import com.example.pet.parent.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testGetAllPosts() throws Exception {
        List<Post> posts = Arrays.asList(
                new Post(1,
                        new Users(
                                1, "User1", "email1", "password1",
                                "img1", "bio1"),
                        "Content1",
                        "postImg1",
                        LocalDateTime.of(2024, 7, 22, 14, 26, 52),
                        10L, 5L, 2L),
                new Post(2,
                        new Users(
                                2, "User2", "email2", "password2",
                                "img2", "bio2"),
                        "Content2",
                        "postImg2",
                        LocalDateTime.of(2024, 5, 13, 7, 23, 45),
                        5L, 2L, 7L));
        when(postService.getAllPosts()).thenReturn(posts);

        mockMvc.perform(post("/post/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].postId", is(1)))
                .andExpect(jsonPath("$[0].users.userId", is(1)))
                .andExpect(jsonPath("$[0].users.username", is("User1")))
                .andExpect(jsonPath("$[0].users.email", is("email1")))
                .andExpect(jsonPath("$[0].users.password", is("password1")))
                .andExpect(jsonPath("$[0].users.profilePicture", is("img1")))
                .andExpect(jsonPath("$[0].users.bio", is("bio1")))
                .andExpect(jsonPath("$[0].content", is("Content1")))
                .andExpect(jsonPath("$[0].imageUrl", is("postImg1")))
                .andExpect(jsonPath("$[0].createdAt[0]", is(2024)))
                .andExpect(jsonPath("$[0].createdAt[1]", is(7)))
                .andExpect(jsonPath("$[0].createdAt[2]", is(22)))
                .andExpect(jsonPath("$[0].createdAt[3]", is(14)))
                .andExpect(jsonPath("$[0].createdAt[4]", is(26)))
                .andExpect(jsonPath("$[0].createdAt[5]", is(52)))
                .andExpect(jsonPath("$[0].likeCount", is(10)))
                .andExpect(jsonPath("$[0].commentCount", is(5)))
                .andExpect(jsonPath("$[0].savesCount", is(2)))
                .andExpect(jsonPath("$[1].postId", is(2)))
                .andExpect(jsonPath("$[1].users.userId", is(2)))
                .andExpect(jsonPath("$[1].users.username", is("User2")))
                .andExpect(jsonPath("$[1].users.email", is("email2")))
                .andExpect(jsonPath("$[1].users.password", is("password2")))
                .andExpect(jsonPath("$[1].users.profilePicture", is("img2")))
                .andExpect(jsonPath("$[1].users.bio", is("bio2")))
                .andExpect(jsonPath("$[1].content", is("Content2")))
                .andExpect(jsonPath("$[1].imageUrl", is("postImg2")))
                .andExpect(jsonPath("$[1].createdAt[0]", is(2024)))
                .andExpect(jsonPath("$[1].createdAt[1]", is(5)))
                .andExpect(jsonPath("$[1].createdAt[2]", is(13)))
                .andExpect(jsonPath("$[1].createdAt[3]", is(7)))
                .andExpect(jsonPath("$[1].createdAt[4]", is(23)))
                .andExpect(jsonPath("$[1].createdAt[5]", is(45)))
                .andExpect(jsonPath("$[1].likeCount", is(5)))
                .andExpect(jsonPath("$[1].commentCount", is(2)))
                .andExpect(jsonPath("$[1].savesCount", is(7)));

        verify(postService, times(1)).getAllPosts();
    }

    @Test
    void testGetAllPostsPageWise_ValidRequest() throws Exception {
        PostDTO postDTO1 = new PostDTO(1, "Content1", "img1", 10L, 5L,
                2L, new UserDTO(1, "User1", "email1",
                        "password1", "userImg1", "bio1"),
                LocalDateTime.of(2024, 7, 22, 14, 26, 52));
        PostDTO postDTO2 = new PostDTO(2, "Content2", "img2", 8L, 3L,
                1L, new UserDTO(2, "User2", "email2",
                        "password2", "userImg2", "bio2"),
                LocalDateTime.of(2024, 5, 13, 7, 23, 45));
        List<PostDTO> posts = Arrays.asList(postDTO1, postDTO2);
        Page<PostDTO> page = new PageImpl<>(posts, PageRequest.of(0, 10), posts.size());
        PostPageRequest postPageRequest = new PostPageRequest();
        postPageRequest.setPage(0);
        postPageRequest.setLimit(10);
        Mockito.when(postService.getAllPosts(Mockito.any(Pageable.class))).thenReturn(page);
        mockMvc.perform(post("/post/all/page")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(postPageRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].postId", is(1)))
                .andExpect(jsonPath("$.content[1].postId", is(2)));
        Mockito.verify(postService, Mockito.times(1)).getAllPosts(Mockito.any(Pageable.class));
    }

    @Test
    void testGetAllPostsPageWise_NoPosts() throws Exception {
        Page<PostDTO> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
        PostPageRequest postPageRequest = new PostPageRequest();
        postPageRequest.setPage(0);
        postPageRequest.setLimit(10);
        Mockito.when(postService.getAllPosts(Mockito.any(Pageable.class))).thenReturn(page);
        mockMvc.perform(post("/post/all/page")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(postPageRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
        Mockito.verify(postService, Mockito.times(1)).getAllPosts(Mockito.any(Pageable.class));
    }

    @Test
    void testGetAllPostsPageWise_InvalidPageNumber() throws Exception {
        PostPageRequest postPageRequest = new PostPageRequest();
        postPageRequest.setPage(-1);
        postPageRequest.setLimit(10);
        mockMvc.perform(post("/post/all/page")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postPageRequest)))
                .andExpect(status().isBadRequest());
        Mockito.verify(postService, Mockito.times(0)).getAllPosts(Mockito.any(Pageable.class));
    }

    @Test
    void testGetAllPostsPageWise_InvalidLimit() throws Exception {
        PostPageRequest postPageRequest = new PostPageRequest();
        postPageRequest.setPage(0);
        postPageRequest.setLimit(0);
        mockMvc.perform(post("/post/all/page")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(postPageRequest)))
                .andExpect(status().isBadRequest());
        Mockito.verify(postService, Mockito.times(0)).getAllPosts(Mockito.any(Pageable.class));
    }

    @Test
    void testGetAllPostsPageWise_MissingFields() throws Exception {
        String request = "{}";
        mockMvc.perform(post("/post/all/page")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request))
                .andExpect(status().isBadRequest());
        Mockito.verify(postService, Mockito.times(0)).getAllPosts(Mockito.any(Pageable.class));
    }

    @Test
    void testGetAllPostsPageWise_LargeLimit() throws Exception {
        PostDTO postDTO = new PostDTO(1, "Content", "img", 10L, 5L,
                2L, new UserDTO(1, "User", "email",
                "password", "userImg", "bio"),
                LocalDateTime.of(2024, 7, 22, 14, 26, 52));
        List<PostDTO> posts = new ArrayList<>();
        for (int i = 1; i <= 100; i++)
            posts.add(postDTO);
        Page<PostDTO> page = new PageImpl<>(posts, PageRequest.of(0, 10), posts.size());
        PostPageRequest postPageRequest = new PostPageRequest();
        postPageRequest.setPage(0);
        postPageRequest.setLimit(100);
        Mockito.when(postService.getAllPosts(Mockito.any(Pageable.class))).thenReturn(page);
        mockMvc.perform(post("/post/all/page")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postPageRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(100)));
        Mockito.verify(postService, Mockito.times(1)).getAllPosts(Mockito.any(Pageable.class));
    }

    @Test
    void testGetallPostsPageWise_DifferentPageNumbers() throws Exception {
        PostDTO postDTO1 = new PostDTO(1, "Content1", "img1", 10L, 5L,
                2L, new UserDTO(1, "User1", "email1",
                "password1", "userImg1", "bio1"),
                LocalDateTime.of(2024, 7, 22, 14, 26, 52));
        PostDTO postDTO2 = new PostDTO(2, "Content2", "img2", 8L, 3L,
                1L, new UserDTO(2, "User2", "email2",
                "password2", "userImg2", "bio2"),
                LocalDateTime.of(2024, 5, 13, 7, 23, 45));
        List<PostDTO> postsPage1 = Arrays.asList(postDTO1, postDTO2);
        Page<PostDTO> page1 = new PageImpl<>(postsPage1, PageRequest.of(0, 2), postsPage1.size());
        PostDTO postDTO3 = new PostDTO(3, "Content3", "img3", 89L, 50L,
                25L, new UserDTO(3, "User3", "email3",
                "password3", "userImg3", "bio3"),
                LocalDateTime.of(2024, 8, 15, 9, 34, 25));
        PostDTO postDTO4 = new PostDTO(4, "Content4", "img4", 67L, 98L,
                50L, new UserDTO(4, "User4", "email4",
                "password4", "userImg4", "bio4"),
                LocalDateTime.of(2024, 10, 25, 12, 48, 36));
        List<PostDTO> postsPage2 = Arrays.asList(postDTO3, postDTO4);
        Page<PostDTO> page2 = new PageImpl<>(postsPage2, PageRequest.of(1, 2), postsPage2.size());
        Mockito.when(postService.getAllPosts(PageRequest.of(0, 2))).thenReturn(page1);
        Mockito.when(postService.getAllPosts(PageRequest.of(1, 2))).thenReturn(page2);
        PostPageRequest postPageRequest1 = new PostPageRequest();
        postPageRequest1.setPage(0);
        postPageRequest1.setLimit(2);
        PostPageRequest postPageRequest2 = new PostPageRequest();
        postPageRequest2.setPage(1);
        postPageRequest2.setLimit(2);
        mockMvc.perform(post("/post/all/page")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postPageRequest1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].postId", is(1)))
                .andExpect(jsonPath("$.content[1].postId", is(2)));
        mockMvc.perform(post("/post/all/page")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postPageRequest2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].postId", is(3)))
                .andExpect(jsonPath("$.content[1].postId", is(4)));
        Mockito.verify(postService, Mockito.times(2)).getAllPosts(Mockito.any(Pageable.class));
    }

    @Test
    void testGetPostById() throws Exception {
        PostIdRequest postIdRequest = new PostIdRequest();
        postIdRequest.setPostId(1);
        Post post = new Post(1, new Users(1, "User1", "email1", "password1",
                "img1", "bio1"), "Content1", "postImg1",
                LocalDateTime.of(2024, 7, 22, 14, 26, 52),
                10L, 5L, 2L);
        Mockito.when(postService.getPostById(postIdRequest.getPostId())).thenReturn(Optional.of(post));
        mockMvc.perform(post("/post/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(postIdRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId", is(1)))
                .andExpect(jsonPath("$.users.userId", is(1)))
                .andExpect(jsonPath("$.users.username", is("User1")))
                .andExpect(jsonPath("$.users.email", is("email1")))
                .andExpect(jsonPath("$.users.password", is("password1")))
                .andExpect(jsonPath("$.users.profilePicture", is("img1")))
                .andExpect(jsonPath("$.users.bio", is("bio1")))
                .andExpect(jsonPath("$.content", is("Content1")))
                .andExpect(jsonPath("$.imageUrl", is("postImg1")))
                .andExpect(jsonPath("$.createdAt[0]", is(2024)))
                .andExpect(jsonPath("$.createdAt[1]", is(7)))
                .andExpect(jsonPath("$.createdAt[2]", is(22)))
                .andExpect(jsonPath("$.createdAt[3]", is(14)))
                .andExpect(jsonPath("$.createdAt[4]", is(26)))
                .andExpect(jsonPath("$.createdAt[5]", is(52)))
                .andExpect(jsonPath("$.likeCount", is(10)))
                .andExpect(jsonPath("$.commentCount", is(5)))
                .andExpect(jsonPath("$.savesCount", is(2)));
        Mockito.verify(postService, Mockito.times(1)).getPostById(postIdRequest.getPostId());
    }

    @Test
    void testCreatePost() throws Exception {
        Post post = new Post(1, new Users(1, "User1", "email1", "password1",
                "img1", "bio1"), "Content1", "postImg1",
                LocalDateTime.of(2024, 7, 22, 14, 26, 52),
                10L, 5L, 2L);

        Mockito.doReturn(post).when(postService).createPost(any(Post.class));

        mockMvc.perform(post("/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId", is(1)))
                .andExpect(jsonPath("$.users.userId", is(1)))
                .andExpect(jsonPath("$.users.username", is("User1")))
                .andExpect(jsonPath("$.users.email", is("email1")))
                .andExpect(jsonPath("$.users.password", is("password1")))
                .andExpect(jsonPath("$.users.profilePicture", is("img1")))
                .andExpect(jsonPath("$.users.bio", is("bio1")))
                .andExpect(jsonPath("$.content", is("Content1")))
                .andExpect(jsonPath("$.imageUrl", is("postImg1")))
                .andExpect(jsonPath("$.createdAt[0]", is(2024)))
                .andExpect(jsonPath("$.createdAt[1]", is(7)))
                .andExpect(jsonPath("$.createdAt[2]", is(22)))
                .andExpect(jsonPath("$.createdAt[3]", is(14)))
                .andExpect(jsonPath("$.createdAt[4]", is(26)))
                .andExpect(jsonPath("$.createdAt[5]", is(52)))
                .andExpect(jsonPath("$.likeCount", is(10)))
                .andExpect(jsonPath("$.commentCount", is(5)))
                .andExpect(jsonPath("$.savesCount", is(2)));

        Mockito.verify(postService, Mockito.times(1)).createPost(any(Post.class));
    }

    @Test
    void testUpdatePost() throws Exception {
        PostEditRequest postEditRequest = new PostEditRequest();
        postEditRequest.setPostId(1);
        Post post = new Post(1, new Users(1, "User1", "email1", "password1",
                "img1", "bio1"), "Updated Content", "updatedImg",
                LocalDateTime.of(2024, 7, 22, 14, 26, 52),
                10L, 5L, 2L);
        postEditRequest.setPost(post);
        Mockito.when(postService.updatePost(Mockito.eq(1), Mockito.any(Post.class)))
                        .thenReturn(post);
        mockMvc.perform(put("/post/edit")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(postEditRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId", is(1)))
                .andExpect(jsonPath("$.users.userId", is(1)))
                .andExpect(jsonPath("$.users.username", is("User1")))
                .andExpect(jsonPath("$.users.email", is("email1")))
                .andExpect(jsonPath("$.users.password", is("password1")))
                .andExpect(jsonPath("$.users.profilePicture", is("img1")))
                .andExpect(jsonPath("$.users.bio", is("bio1")))
                .andExpect(jsonPath("$.content", is("Updated Content")))
                .andExpect(jsonPath("$.imageUrl", is("updatedImg")))
                .andExpect(jsonPath("$.createdAt[0]", is(2024)))
                .andExpect(jsonPath("$.createdAt[1]", is(7)))
                .andExpect(jsonPath("$.createdAt[2]", is(22)))
                .andExpect(jsonPath("$.createdAt[3]", is(14)))
                .andExpect(jsonPath("$.createdAt[4]", is(26)))
                .andExpect(jsonPath("$.createdAt[5]", is(52)))
                .andExpect(jsonPath("$.likeCount", is(10)))
                .andExpect(jsonPath("$.commentCount", is(5)))
                .andExpect(jsonPath("$.savesCount", is(2)));
        Mockito.verify(postService, Mockito.times(1))
                .updatePost(Mockito.eq(1), Mockito.any(Post.class));
    }

    @Test
    void testDeletePost() throws Exception {
        PostIdRequest postIdRequest = new PostIdRequest();
        postIdRequest.setPostId(1);
        mockMvc.perform(delete("/post/delete")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(postIdRequest)))
                .andExpect(status().isOk());
        Mockito.verify(postService, Mockito.times(1)).deletePost(postIdRequest.getPostId());
    }

    @Test
    void testLikePost() throws Exception {
        ActionRequest actionRequest = new ActionRequest();
        actionRequest.setPostId(1);
        actionRequest.setUserId(1);
        mockMvc.perform(post("/post/like")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(actionRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Post liked successfully"));
        Mockito.verify(postService, Mockito.times(1))
                .likePost(actionRequest.getPostId(), actionRequest.getUserId());
    }

    @Test
    void testUnlikePost() throws Exception {
        ActionRequest actionRequest = new ActionRequest();
        actionRequest.setPostId(1);
        actionRequest.setUserId(1);
        mockMvc.perform(post("/post/unlike")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actionRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Post unliked successfully"));
        Mockito.verify(postService, Mockito.times(1))
                .unlikePost(actionRequest.getPostId(), actionRequest.getUserId());
    }

    @Test
    void testSavePost() throws Exception {
        ActionRequest actionRequest = new ActionRequest();
        actionRequest.setPostId(1);
        actionRequest.setUserId(1);
        mockMvc.perform(post("/post/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actionRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Post saved successfully"));
        Mockito.verify(postService, Mockito.times(1))
                .savePost(actionRequest.getPostId(), actionRequest.getUserId());
    }

    @Test
    void testUnsavePost() throws Exception {
        ActionRequest actionRequest = new ActionRequest();
        actionRequest.setPostId(1);
        actionRequest.setUserId(1);
        mockMvc.perform(post("/post/unsave")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actionRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Post unsaved successfully"));
        Mockito.verify(postService, Mockito.times(1))
                .unsavePost(actionRequest.getPostId(), actionRequest.getUserId());
    }

    @Test
    void testGetLikedPostsByUser() throws Exception {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1);
        Post post1 = new Post(1, new Users(1, "User1", "email1", "password1",
                "img1", "bio1"), "Content1", "postImg1",
                LocalDateTime.of(2024, 7, 22, 14, 26, 52),
                10L, 5L, 2L);
        Post post2 = new Post(2, new Users(2, "User2", "email2", "password2",
                "img2", "bio2"), "Content2", "postImg2",
                LocalDateTime.of(2024, 5, 9, 7, 14, 36),
                5L, 2L, 7L);
        List<Post> posts = Arrays.asList(post1, post2);
        Mockito.when(postService.getLikedPostsByUser(userIdRequest.getUserId())).thenReturn(posts);
        mockMvc.perform(post("/post/user/liked")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userIdRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].postId", is(1)))
                .andExpect(jsonPath("$[0].users.userId", is(1)))
                .andExpect(jsonPath("$[0].users.username", is("User1")))
                .andExpect(jsonPath("$[0].users.email", is("email1")))
                .andExpect(jsonPath("$[0].users.password", is("password1")))
                .andExpect(jsonPath("$[0].users.profilePicture", is("img1")))
                .andExpect(jsonPath("$[0].users.bio", is("bio1")))
                .andExpect(jsonPath("$[0].content", is("Content1")))
                .andExpect(jsonPath("$[0].imageUrl", is("postImg1")))
                .andExpect(jsonPath("$[0].createdAt[0]", is(2024)))
                .andExpect(jsonPath("$[0].createdAt[1]", is(7)))
                .andExpect(jsonPath("$[0].createdAt[2]", is(22)))
                .andExpect(jsonPath("$[0].createdAt[3]", is(14)))
                .andExpect(jsonPath("$[0].createdAt[4]", is(26)))
                .andExpect(jsonPath("$[0].createdAt[5]", is(52)))
                .andExpect(jsonPath("$[0].likeCount", is(10)))
                .andExpect(jsonPath("$[0].commentCount", is(5)))
                .andExpect(jsonPath("$[0].savesCount", is(2)))
                .andExpect(jsonPath("$[1].postId", is(2)))
                .andExpect(jsonPath("$[1].users.userId", is(2)))
                .andExpect(jsonPath("$[1].users.username", is("User2")))
                .andExpect(jsonPath("$[1].users.email", is("email2")))
                .andExpect(jsonPath("$[1].users.password", is("password2")))
                .andExpect(jsonPath("$[1].users.profilePicture", is("img2")))
                .andExpect(jsonPath("$[1].users.bio", is("bio2")))
                .andExpect(jsonPath("$[1].content", is("Content2")))
                .andExpect(jsonPath("$[1].imageUrl", is("postImg2")))
                .andExpect(jsonPath("$[1].createdAt[0]", is(2024)))
                .andExpect(jsonPath("$[1].createdAt[1]", is(5)))
                .andExpect(jsonPath("$[1].createdAt[2]", is(9)))
                .andExpect(jsonPath("$[1].createdAt[3]", is(7)))
                .andExpect(jsonPath("$[1].createdAt[4]", is(14)))
                .andExpect(jsonPath("$[1].createdAt[5]", is(36)))
                .andExpect(jsonPath("$[1].likeCount", is(5)))
                .andExpect(jsonPath("$[1].commentCount", is(2)))
                .andExpect(jsonPath("$[1].savesCount", is(7)));
        Mockito.verify(postService, Mockito.times(1))
                .getLikedPostsByUser(userIdRequest.getUserId());
    }

    @Test
    void testGetSavedPostsByUser() throws Exception {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1);
        Post post1 = new Post(1, new Users(1, "User1", "email1", "password1",
                "img1", "bio1"), "Content1", "postImg1",
                LocalDateTime.of(2024, 7, 22, 14, 26, 52),
                10L, 5L, 2L);
        Post post2 = new Post(2, new Users(2, "User2", "email2", "password2",
                "img2", "bio2"), "Content2", "postImg2",
                LocalDateTime.of(2024, 5, 9, 7, 14, 36),
                5L, 2L, 7L);
        List<Post> posts = Arrays.asList(post1, post2);
        Mockito.when(postService.getSavedPostsByUser(userIdRequest.getUserId())).thenReturn(posts);
        mockMvc.perform(post("/post/user/saved")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userIdRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].postId", is(1)))
                .andExpect(jsonPath("$[0].users.userId", is(1)))
                .andExpect(jsonPath("$[0].users.username", is("User1")))
                .andExpect(jsonPath("$[0].users.email", is("email1")))
                .andExpect(jsonPath("$[0].users.password", is("password1")))
                .andExpect(jsonPath("$[0].users.profilePicture", is("img1")))
                .andExpect(jsonPath("$[0].users.bio", is("bio1")))
                .andExpect(jsonPath("$[0].content", is("Content1")))
                .andExpect(jsonPath("$[0].imageUrl", is("postImg1")))
                .andExpect(jsonPath("$[0].createdAt[0]", is(2024)))
                .andExpect(jsonPath("$[0].createdAt[1]", is(7)))
                .andExpect(jsonPath("$[0].createdAt[2]", is(22)))
                .andExpect(jsonPath("$[0].createdAt[3]", is(14)))
                .andExpect(jsonPath("$[0].createdAt[4]", is(26)))
                .andExpect(jsonPath("$[0].createdAt[5]", is(52)))
                .andExpect(jsonPath("$[0].likeCount", is(10)))
                .andExpect(jsonPath("$[0].commentCount", is(5)))
                .andExpect(jsonPath("$[0].savesCount", is(2)))
                .andExpect(jsonPath("$[1].postId", is(2)))
                .andExpect(jsonPath("$[1].users.userId", is(2)))
                .andExpect(jsonPath("$[1].users.username", is("User2")))
                .andExpect(jsonPath("$[1].users.email", is("email2")))
                .andExpect(jsonPath("$[1].users.password", is("password2")))
                .andExpect(jsonPath("$[1].users.profilePicture", is("img2")))
                .andExpect(jsonPath("$[1].users.bio", is("bio2")))
                .andExpect(jsonPath("$[1].content", is("Content2")))
                .andExpect(jsonPath("$[1].imageUrl", is("postImg2")))
                .andExpect(jsonPath("$[1].createdAt[0]", is(2024)))
                .andExpect(jsonPath("$[1].createdAt[1]", is(5)))
                .andExpect(jsonPath("$[1].createdAt[2]", is(9)))
                .andExpect(jsonPath("$[1].createdAt[3]", is(7)))
                .andExpect(jsonPath("$[1].createdAt[4]", is(14)))
                .andExpect(jsonPath("$[1].createdAt[5]", is(36)))
                .andExpect(jsonPath("$[1].likeCount", is(5)))
                .andExpect(jsonPath("$[1].commentCount", is(2)))
                .andExpect(jsonPath("$[1].savesCount", is(7)));
        Mockito.verify(postService, Mockito.times(1))
                .getSavedPostsByUser(userIdRequest.getUserId());
    }

}
