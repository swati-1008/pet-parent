package com.example.pet.parent.request.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCommentRequest {
    private int postId;
    private int userId;
    private String commentText;
}
