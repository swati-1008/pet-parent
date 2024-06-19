package com.example.pet.parent.request.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostIdRequest {
    private int postId;
    private int page;
    private int limit;
}
