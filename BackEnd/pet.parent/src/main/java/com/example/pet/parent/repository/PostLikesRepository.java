package com.example.pet.parent.repository;

import com.example.pet.parent.model.PostLikeId;
import com.example.pet.parent.model.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, PostLikeId> {
    List<PostLikes> findByUserUserId (Integer userId);
}
