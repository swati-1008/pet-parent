package com.example.pet.parent.controller;

import com.example.pet.parent.model.Post;
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

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/get")
    public Optional<Post> getPostById(@RequestParam(name = "id") int id) {
        return postService.getPostById(id);
    }

    @PostMapping("/create")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/edit")
    public Post updatePost(@RequestParam(name = "id") int id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/delete")
    public void deletePost(@RequestParam(name = "id") int id) {
        postService.deletePost(id);
    }
}

