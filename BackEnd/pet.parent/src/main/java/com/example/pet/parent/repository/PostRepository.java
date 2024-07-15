package com.example.pet.parent.repository;

import com.example.pet.parent.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUsersUserId(Integer userId);
    Page<Post> findAll (Pageable pageable);

    @Query("SELECT COUNT(pl) FROM PostLikes pl WHERE pl.post.postId = :postId")
    Long countLikesByPostId(Integer postId);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.post.postId = :postId")
    Long countCommentsByPostId(Integer postId);

    @Query("SELECT COUNT(ps) FROM PostSaves ps WHERE ps.post.postId = :postId")
    Long countSavesByPostId(Integer postId);
}
