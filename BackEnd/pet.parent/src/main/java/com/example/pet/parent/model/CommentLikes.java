package com.example.pet.parent.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "comment_likes")
@IdClass(CommentLikeId.class)
public class CommentLikes {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Id
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

}
