package com.example.pet.parent.controller;

import com.example.pet.parent.model.Suggestions;
import com.example.pet.parent.request.Suggestions.SuggestionsRequest;
import com.example.pet.parent.service.SuggestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suggestions")
public class SuggestionsController {
    @Autowired
    private SuggestionsService suggestionsService;

    @PostMapping ("/get")
    public List<Suggestions> getSuggestions (@RequestBody SuggestionsRequest suggestionsRequest) {
        return suggestionsService.getSuggestions(suggestionsRequest.getQuery());
    }

    @PostMapping ("/trending")
    public List<Suggestions> getTrendingSuggestions () {
        return suggestionsService.getTrendingSuggestions();
    }
}
