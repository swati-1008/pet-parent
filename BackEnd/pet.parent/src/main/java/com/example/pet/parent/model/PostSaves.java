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
@IdClass(PostSaves.class)
public class PostSaves {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
