package com.example.pet.parent.request.Comment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCommentRequest {

    @NotNull(message = "Post ID is required")
    private int postId;

    @NotNull(message = "User ID is required")
    private int userId;

    @NotNull(message = "Comment text is required")
    @Size(min = 1, message = "Comment text must not be empty")
    private String commentText;
}
