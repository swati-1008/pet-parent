package com.example.pet.parent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private int postId;
    private String content;
    private String imageUrl;
    private Long likeCount;
    private Long commentCount;
    private Long savesCount;
    private UserDTO user;
    private LocalDateTime createdAt;
}
