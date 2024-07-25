package com.example.pet.parent.service.impl;

import com.example.pet.parent.model.Comment;
import com.example.pet.parent.model.Post;
import com.example.pet.parent.model.Users;
import com.example.pet.parent.repository.CommentRepository;
import com.example.pet.parent.repository.PostRepository;
import com.example.pet.parent.repository.UsersRepository;
import com.example.pet.parent.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Comment> getCommentsByPostId (int postId) {
        return commentRepository.findByPostPostId(postId);
    }

    @Override
    public Comment addComment (int postId, int userId, String commentText) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setCommentText(commentText);

        return commentRepository.save(comment);
    }
}
