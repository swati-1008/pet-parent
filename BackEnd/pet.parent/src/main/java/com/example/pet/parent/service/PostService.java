package com.example.pet.parent.service;

import com.example.pet.parent.dto.PostDTO;
import com.example.pet.parent.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();
    Page<PostDTO> getAllPosts(Pageable pageable);
    Optional<Post> getPostById(int id);
    Post createPost(Post post);
    Post updatePost(int id, Post post);
    void deletePost(int id);
    void likePost(int postId, int userId);
    void unlikePost(int postId, int userId);
    void savePost(int postId, int userId);
    void unsavePost(int postId, int userId);
    List<Post> getLikedPostsByUser (int userId);
    List<Post> getSavedPostsByUser (int userId);
}