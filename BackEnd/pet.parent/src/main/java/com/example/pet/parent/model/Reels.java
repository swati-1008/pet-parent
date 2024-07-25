package com.example.pet.parent.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "reels")

public class Reels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "profile_picture")
    private String profilePicture;

    @OneToMany(mappedBy = "reels", cascade = CascadeType.ALL)
    private List<Media> media;

}
