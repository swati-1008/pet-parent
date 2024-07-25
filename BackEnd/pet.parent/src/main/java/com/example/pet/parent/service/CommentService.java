package com.example.pet.parent.service;

import com.example.pet.parent.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByPostId (int postId);
    Comment addComment (int postId, int userId, String commentText);
}