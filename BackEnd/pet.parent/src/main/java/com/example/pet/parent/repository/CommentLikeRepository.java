package com.example.pet.parent.repository;

import com.example.pet.parent.model.CommentLikeId;
import com.example.pet.parent.model.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLikes, CommentLikeId> {
}
