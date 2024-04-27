package com.example.pet.parent.service.impl;

import com.example.pet.parent.model.Post;
import com.example.pet.parent.repository.PostRepository;
import com.example.pet.parent.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl (PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(int id, Post post) {
        Optional<Post> currentPost = postRepository.findById(id);
        if (currentPost.isPresent()) {
            Post tempPost = currentPost.get();
            tempPost.setContent(post.getContent());
            if (post.getUsers() != null)
                tempPost.setUsers(post.getUsers());
            return postRepository.save(tempPost);
        }
        else {
            throw new RuntimeException(("Post not found with id: " + id));
        }
    }

    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }
}