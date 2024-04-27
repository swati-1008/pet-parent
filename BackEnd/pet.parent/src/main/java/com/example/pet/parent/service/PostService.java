package com.example.pet.parent.service;

import com.example.pet.parent.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(int id);
    Post createPost(Post post);
    Post updatePost(int id, Post post);
    void deletePost(int id);
}