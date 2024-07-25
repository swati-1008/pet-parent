package com.example.pet.parent.repository;

import com.example.pet.parent.model.Reels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReelsRepository extends JpaRepository<Reels, Integer> {
}
