package com.example.pet.parent.controller;

import com.example.pet.parent.model.Post;
import com.example.pet.parent.request.Post.PostEditRequest;
import com.example.pet.parent.request.Post.PostIdRequest;
import com.example.pet.parent.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
