package com.example.pet.parent.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suggestions")
public class Suggestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "suggestion")
    private String suggestion;

    @Column(name = "is_trending")
    private Boolean isTrending;
}