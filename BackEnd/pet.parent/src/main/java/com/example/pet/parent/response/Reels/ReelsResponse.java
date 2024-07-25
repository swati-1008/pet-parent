package com.example.pet.parent.response.Reels;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReelsResponse {
    private int id;
    private String username;
    private String profilePicture;
    private List<String> media;
}
