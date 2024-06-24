package com.example.pet.parent.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "post_saves")
@IdClass(PostSavesId.class)
public class PostSaves {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
