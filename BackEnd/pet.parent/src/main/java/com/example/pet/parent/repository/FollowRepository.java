package com.example.pet.parent.repository;

import com.example.pet.parent.model.Follow;
import com.example.pet.parent.model.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    List<Follow> findByFollowerUserId (Integer followerId);
    List<Follow> findByFolloweeUserId (Integer followeeId);

    @Query("SELECT f.id.followeeId FROM Follow f WHERE f.id.followerId = :userId")
    List<Integer> findPeopleYouMayKnow (@Param("userId") int userId);
}
