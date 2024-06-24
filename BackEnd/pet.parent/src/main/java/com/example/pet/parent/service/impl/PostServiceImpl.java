package com.example.pet.parent.service.impl;

import com.example.pet.parent.model.*;
import com.example.pet.parent.repository.PostLikesRepository;
import com.example.pet.parent.repository.PostRepository;
import com.example.pet.parent.repository.PostSavesRepository;
import com.example.pet.parent.repository.UsersRepository;
import com.example.pet.parent.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikesRepository postLikesRepository;

    @Autowired
    private PostSavesRepository postSavesRepository;

    @Autowired
    private UsersRepository usersRepository;

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
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
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

    @Override
    public void likePost (int postId, int userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        PostLikes postLikes = new PostLikes();
        postLikes.setPost(post);
        postLikes.setUser(user);
        postLikesRepository.save(postLikes);
    }

    @Override
    public void unlikePost (int postId, int userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        postLikesRepository.deleteById(new PostLikeId(user.getUserId(), post.getPostId()));
    }

    @Override
    public void savePost (int postId, int userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        PostSaves postSaves = new PostSaves();
        postSaves.setPost(post);
        postSaves.setUser(user);
        postSavesRepository.save(postSaves);
    }

    @Override
    public void unsavePost (int postId, int userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        postSavesRepository.deleteById(new PostSavesId(user.getUserId(), post.getPostId()));
    }

    @Override
    public List<Post> getLikedPostsByUser (int userId) {
        return postLikesRepository.findByUserUserId(userId).stream()
                .map(PostLikes::getPost)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getSavedPostsByUser (int userId) {
        return postSavesRepository.findByUserUserId(userId).stream()
                .map(PostSaves::getPost)
                .collect(Collectors.toList());
    }
}