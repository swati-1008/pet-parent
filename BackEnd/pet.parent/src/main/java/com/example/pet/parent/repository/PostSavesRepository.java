package com.example.pet.parent.repository;

import com.example.pet.parent.model.PostSaves;
import com.example.pet.parent.model.PostSavesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostSavesRepository extends JpaRepository<PostSaves, PostSavesId> {
}