package com.example.pet.parent.service;

import com.example.pet.parent.model.Suggestions;

import java.util.List;

public interface SuggestionsService {
    List<Suggestions> getSuggestions(String query);
    List<Suggestions> getTrendingSuggestions();
}
