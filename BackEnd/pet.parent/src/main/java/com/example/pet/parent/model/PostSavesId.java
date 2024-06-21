package com.example.pet.parent.model;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class PostSavesId implements Serializable {
    private Integer user;
    private Integer post;
}