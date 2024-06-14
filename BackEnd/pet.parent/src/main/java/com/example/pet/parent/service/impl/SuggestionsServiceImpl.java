package com.example.pet.parent.service.impl;

import com.example.pet.parent.model.Suggestions;
import com.example.pet.parent.repository.SuggestionsRepository;
import com.example.pet.parent.service.SuggestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionsServiceImpl implements SuggestionsService {
    private final SuggestionsRepository suggestionsRepository;

    @Autowired
    public SuggestionsServiceImpl(SuggestionsRepository suggestionsRepository) {
        this.suggestionsRepository = suggestionsRepository;
    }

    @Override
    public List<Suggestions> getSuggestions (String query) {
        return suggestionsRepository.findBySuggestionContainingIgnoreCase(query);
    }

    @Override
    public List<Suggestions> getTrendingSuggestions() {
        return suggestionsRepository.findByIsTrendingTrue();
    }
}