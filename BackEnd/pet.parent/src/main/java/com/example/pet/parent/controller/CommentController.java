package com.example.pet.parent.controller;

import com.example.pet.parent.model.Comment;
import com.example.pet.parent.request.Comment.AddCommentRequest;
import com.example.pet.parent.request.Comment.CommentRequest;
import com.example.pet.parent.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/get")
    public List<Comment> getCommentsByPost (@RequestBody CommentRequest commentRequest) {
        return commentService.getCommentsByPostId(commentRequest.getPostId());
    }

    @PostMapping("/add")
    public Comment addComment (@RequestBody AddCommentRequest addCommentRequest) {
        return commentService.addComment(addCommentRequest.getPostId(), addCommentRequest.getUserId(), addCommentRequest.getCommentText());
    }
}
