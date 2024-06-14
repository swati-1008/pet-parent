package com.example.pet.parent.controller;

import com.example.pet.parent.model.Suggestions;
import com.example.pet.parent.service.SuggestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/suggestions")
public class SuggestionsController {
    @Autowired
    private SuggestionsService suggestionsService;

    @PostMapping ("/get")
    public List<Suggestions> getSuggestions (@RequestBody Map<String, String> request) {
        String query = request.get("query");
        return suggestionsService.getSuggestions(query);
    }

    @PostMapping ("/trending")
    public List<Suggestions> getTrendingSuggestions () {
        return suggestionsService.getTrendingSuggestions();
    }
}
