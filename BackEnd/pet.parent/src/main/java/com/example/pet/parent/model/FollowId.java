package com.example.pet.parent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FollowId implements Serializable {

    @Column(name = "follower_id")
    private int followerId;

    @Column(name = "followee_id")
    private int followeeId;

}