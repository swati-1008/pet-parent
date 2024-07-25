package com.example.pet.parent.request.Post;

import com.example.pet.parent.model.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostEditRequest {
    private int postId;
    private Post post;
}
