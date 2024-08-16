package com.example.pet.parent.controller;

import com.example.pet.parent.model.Suggestions;
import com.example.pet.parent.request.Suggestions.SuggestionsRequest;
import com.example.pet.parent.service.SuggestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suggestions")
public class SuggestionsController {
    @Autowired
    private SuggestionsService suggestionsService;

    @PostMapping ("/get")
    public ResponseEntity<List<Suggestions>> getSuggestions (@RequestBody SuggestionsRequest suggestionsRequest) {
        if (suggestionsRequest.getQuery() == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(suggestionsService.getSuggestions(suggestionsRequest.getQuery()));
    }

    @PostMapping ("/trending")
    public List<Suggestions> getTrendingSuggestions () {
        return suggestionsService.getTrendingSuggestions();
    }
}
