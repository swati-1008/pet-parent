package com.example.pet.parent.controller;

import com.example.pet.parent.model.Post;
import com.example.pet.parent.request.Post.ActionRequest;
import com.example.pet.parent.request.Post.PostPageRequest;
import com.example.pet.parent.request.Post.PostEditRequest;
import com.example.pet.parent.request.Post.PostIdRequest;
import com.example.pet.parent.request.Users.UserIdRequest;
import com.example.pet.parent.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/all/page")
    public Page<Post> getAllPosts(@RequestBody PostPageRequest postPageRequest) {
        Pageable pageable = PageRequest.of(postPageRequest.getPage(), postPageRequest.getLimit());
        return postService.getAllPosts(pageable);
    }

    @PostMapping("/get")
    public Optional<Post> getPostById(@RequestBody PostIdRequest postIdRequest) {
        return postService.getPostById(postIdRequest.getPostId());
    }

    @PostMapping("/create")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/edit")
    public Post updatePost(@RequestBody PostEditRequest postEditRequest) {
        return postService.updatePost(postEditRequest.getPostId(), postEditRequest.getPost());
    }

    @DeleteMapping("/delete")
    public void deletePost(@RequestBody PostIdRequest postIdRequest) {
        postService.deletePost(postIdRequest.getPostId());
    }

    @PostMapping("/like")
    public ResponseEntity<String> likePost (@RequestBody ActionRequest actionRequest) {
        postService.likePost(actionRequest.getPostId(), actionRequest.getUserId());
        return ResponseEntity.ok("Post liked successfully");
    }

    @PostMapping("/unlike")
    public ResponseEntity<String> unlikePost (@RequestBody ActionRequest actionRequest) {
        postService.unlikePost(actionRequest.getPostId(), actionRequest.getUserId());
        return ResponseEntity.ok("Post unliked successfully");
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePost (@RequestBody ActionRequest actionRequest) {
        postService.savePost(actionRequest.getPostId(), actionRequest.getUserId());
        return ResponseEntity.ok("Post saved successfully");
    }

    @PostMapping("/unsave")
    public ResponseEntity<String> unsavePost (@RequestBody ActionRequest actionRequest) {
        postService.unsavePost(actionRequest.getPostId(), actionRequest.getUserId());
        return ResponseEntity.ok("Post unsaved successfully");
    }

    @PostMapping("/user/liked")
    public List<Post> getLikedPostsByUser (@RequestBody UserIdRequest userIdRequest) {
        return postService.getLikedPostsByUser(userIdRequest.getUserId());
    }

    @PostMapping("/user/saved")
    public List<Post> getSavedPostsByUser (@RequestBody UserIdRequest userIdRequest) {
        return postService.getSavedPostsByUser(userIdRequest.getUserId());
    }
}