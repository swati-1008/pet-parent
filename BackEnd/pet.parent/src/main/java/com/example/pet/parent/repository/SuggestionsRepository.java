package com.example.pet.parent.repository;

import com.example.pet.parent.model.Suggestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionsRepository extends JpaRepository<Suggestions, Long> {
    List<Suggestions> findBySuggestionContainingIgnoreCase(String query);
    List<Suggestions> findByIsTrendingTrue();
}