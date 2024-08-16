package com.example.pet.parent.controller;

import com.example.pet.parent.model.Suggestions;
import com.example.pet.parent.request.Suggestions.SuggestionsRequest;
import com.example.pet.parent.service.SuggestionsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class SuggestionsControllerTest {

    @Mock
    private SuggestionsService suggestionsService;

    @InjectMocks
    private SuggestionsController suggestionsController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(suggestionsController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetSuggestions_ValidQuery() throws Exception {
        SuggestionsRequest suggestionsRequest = new SuggestionsRequest();
        suggestionsRequest.setQuery("example");

        List<Suggestions> suggestions = List.of(new Suggestions(1L, "example suggestion", false));

        Mockito.when(suggestionsService.getSuggestions("example")).thenReturn(suggestions);

        mockMvc.perform(post("/suggestions/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(suggestionsRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].suggestion", is("example suggestion")));

        Mockito.verify(suggestionsService, Mockito.times(1)).getSuggestions("example");
    }

    @Test
    void testGetSuggestions_EmptyQuery() throws Exception {
        SuggestionsRequest request = new SuggestionsRequest();
        request.setQuery("");

        List<Suggestions> suggestions = List.of();

        Mockito.when(suggestionsService.getSuggestions("")).thenReturn(suggestions);

        mockMvc.perform(post("/suggestions/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        Mockito.verify(suggestionsService, Mockito.times(1)).getSuggestions("");
    }

    @Test
    void testGetSuggestions_NullQuery() throws Exception {
        SuggestionsRequest request = new SuggestionsRequest();
        request.setQuery(null);

        mockMvc.perform(post("/suggestions/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        Mockito.verify(suggestionsService, Mockito.times(0)).getSuggestions(null);
    }

    @Test
    void testGetTrendingSuggestions_ValidScenario() throws Exception {
        List<Suggestions> trendingSuggestions = List.of(new Suggestions(1L, "suggestion", true));

        Mockito.when(suggestionsService.getTrendingSuggestions()).thenReturn(trendingSuggestions);

        mockMvc.perform(post("/suggestions/trending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].suggestion", is("suggestion")));

        Mockito.verify(suggestionsService, Mockito.times(1)).getTrendingSuggestions();
    }

    @Test
    void testGetTrendingSuggestions_EmptyList() throws Exception {
        List<Suggestions> trendingSuggestions = List.of();

        Mockito.when(suggestionsService.getTrendingSuggestions()).thenReturn(trendingSuggestions);

        mockMvc.perform(post("/suggestions/trending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        Mockito.verify(suggestionsService, Mockito.times(1)).getTrendingSuggestions();
    }

    @Test
    void testGetSuggestions_ServiceReturnsNull() throws Exception {
        SuggestionsRequest suggestionsRequest = new SuggestionsRequest();
        suggestionsRequest.setQuery("example");

        Mockito.when(suggestionsService.getSuggestions("example")).thenReturn(null);

        mockMvc.perform(post("/suggestions/get")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(suggestionsRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        Mockito.verify(suggestionsService, Mockito.times(1)).getSuggestions("example");
    }

}
