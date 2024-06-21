package com.example.pet.parent.service.impl;

import com.example.pet.parent.model.Post;
import com.example.pet.parent.repository.PostRepository;
import com.example.pet.parent.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        posts.forEach(post -> {
            post.setLikeCount(postRepository.countLikesByPostId(post.getPostId()));
            post.setCommentCount(postRepository.countCommentsByPostId(post.getPostId()));
            post.setSavesCount(postRepository.countSavesByPostId(post.getPostId()));
        });
        return posts;
    }

    @Override
    @Transactional
    public Optional<Post> getPostById(int id) {
        Optional<Post> post = postRepository.findById(id);
        post.ifPresent(p -> {
            p.setLikeCount(postRepository.countLikesByPostId(p.getPostId()));
            p.setCommentCount(postRepository.countCommentsByPostId(p.getPostId()));
            p.setSavesCount(postRepository.countSavesByPostId(p.getPostId()));
        });
        return post;
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