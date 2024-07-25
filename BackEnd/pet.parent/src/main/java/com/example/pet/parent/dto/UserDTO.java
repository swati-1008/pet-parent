package com.example.pet.parent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
    private String bio;
}
