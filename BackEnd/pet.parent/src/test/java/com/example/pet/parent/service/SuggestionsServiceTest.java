package com.example.pet.parent.service;

import com.example.pet.parent.model.Suggestions;
import com.example.pet.parent.repository.SuggestionsRepository;
import com.example.pet.parent.service.impl.SuggestionsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class SuggestionsServiceTest {

    @Mock
    private SuggestionsRepository suggestionsRepository;

    @InjectMocks
    private SuggestionsServiceImpl suggestionsService;

    @Test
    void testGetSuggestions_ReturnsMatchingSuggestions() {
        String query = "Dog";

        Mockito.when(suggestionsRepository.findBySuggestionContainingIgnoreCase(query))
                .thenReturn(List.of(new Suggestions(1L, "Dog Food", false)));

        List<Suggestions> fetchedSuggestions = suggestionsService.getSuggestions(query);

        System.out.println("Suggestions = " + fetchedSuggestions);

        assertEquals(1, fetchedSuggestions.size());
        assertEquals("Dog Food", fetchedSuggestions.get(0).getSuggestion());

        Mockito.verify(suggestionsRepository, Mockito.times(1))
                .findBySuggestionContainingIgnoreCase(query);
    }

    @Test
    void testGetSuggestions_WhenNoMatchFound_ReturnsEmptyList() {
        String query = "Human";

        Mockito.when(suggestionsRepository.findBySuggestionContainingIgnoreCase(query)).thenReturn(List.of());

        List<Suggestions> fetchedSuggestions = suggestionsService.getSuggestions(query);

        assertTrue(fetchedSuggestions.isEmpty());

        Mockito.verify(suggestionsRepository, Mockito.times(1))
                .findBySuggestionContainingIgnoreCase(query);
    }

    @Test
    void testGetTrendingSuggestions_ReturnsTrendingSuggestions() {
        Mockito.when(suggestionsRepository.findByIsTrendingTrue())
                .thenReturn(List.of(new Suggestions(1L, "Dog Food", true)));

        List<Suggestions> fetchedTrendingSuggestions = suggestionsService.getTrendingSuggestions();

        assertEquals(1, fetchedTrendingSuggestions.size());
        assertTrue(fetchedTrendingSuggestions.get(0).getIsTrending());

        Mockito.verify(suggestionsRepository, Mockito.times(1)).findByIsTrendingTrue();
    }

    @Test
    void testGetTrendingSuggestions_WhenNoTrendingSuggestions_ReturnsEmptyList() {
        Mockito.when(suggestionsRepository.findByIsTrendingTrue())
                .thenReturn(List.of());

        List<Suggestions> fetchedTrendingSuggestions = suggestionsService.getTrendingSuggestions();

        assertTrue(fetchedTrendingSuggestions.isEmpty());

        Mockito.verify(suggestionsRepository, Mockito.times(1)).findByIsTrendingTrue();
    }

}
