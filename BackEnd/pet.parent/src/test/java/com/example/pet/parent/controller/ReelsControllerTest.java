package com.example.pet.parent.controller;

import com.example.pet.parent.model.Media;
import com.example.pet.parent.model.Reels;
import com.example.pet.parent.service.MediaService;
import com.example.pet.parent.service.ReelsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ReelsControllerTest {

    @Mock
    private ReelsService reelsService;

    @Mock
    private MediaService mediaService;

    @InjectMocks
    private ReelsController reelsController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reelsController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllReels_ValidScenario() throws Exception {
        Reels reel = new Reels(1, "user1", "img1", null);
        List<Reels> reelsList = List.of(reel);

        Media media1 = new Media(1, reel, "url1");
        Media media2 = new Media(2, reel, "url2");
        List<Media> mediaList = List.of(media1, media2);

        Mockito.when(reelsService.getAllReels()).thenReturn(reelsList);
        Mockito.when(mediaService.getMediaByReelId(1)).thenReturn(mediaList);

        mockMvc.perform(post("/reels/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("user1")))
                .andExpect(jsonPath("$[0].profilePicture", is("img1")))
                .andExpect(jsonPath("$[0].media[0]", is("url1")))
                .andExpect(jsonPath("$[0].media[1]", is("url2")));

        Mockito.verify(reelsService, Mockito.times(1)).getAllReels();
        Mockito.verify(mediaService, Mockito.times(1)).getMediaByReelId(1);
    }

    @Test
    void testGetAllReels_NoReelsFound()  throws Exception {
        Mockito.when(reelsService.getAllReels()).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/reels/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        Mockito.verify(reelsService, Mockito.times(1)).getAllReels();
        Mockito.verify(mediaService, Mockito.times(0)).getMediaByReelId(anyInt());
    }

    @Test
    void testGetAllReels_ReelExistsButNoMedia() throws Exception {
        Reels reel = new Reels(1, "user1", "img1", null);
        List<Reels> reelsList = List.of(reel);

        Mockito.when(reelsService.getAllReels()).thenReturn(reelsList);
        Mockito.when(mediaService.getMediaByReelId(1)).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/reels/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("user1")))
                .andExpect(jsonPath("$[0].profilePicture", is("img1")))
                .andExpect(jsonPath("$[0].media", hasSize(0)));

        Mockito.verify(reelsService, Mockito.times(1)).getAllReels();
        Mockito.verify(mediaService, Mockito.times(1)).getMediaByReelId(1);
    }

    @Test
    void testGetAllReels_MultipleReelsWithMedia() throws Exception {
        Reels reel1 = new Reels(1, "user1", "img1", null);
        Reels reel2 = new Reels(2, "user2", "img2", null);
        List<Reels> reelsList = List.of(reel1, reel2);

        Media media1 = new Media(1, reel1, "url1");
        Media media2 = new Media(2, reel2, "url2");
        List<Media> mediaList1 = List.of(media1);
        List<Media> mediaList2 = List.of(media2);

        Mockito.when(reelsService.getAllReels()).thenReturn(reelsList);
        Mockito.when(mediaService.getMediaByReelId(1)).thenReturn(mediaList1);
        Mockito.when(mediaService.getMediaByReelId(2)).thenReturn(mediaList2);

        mockMvc.perform(post("/reels/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("user1")))
                .andExpect(jsonPath("$[0].profilePicture", is("img1")))
                .andExpect(jsonPath("$[0].media[0]", is("url1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("user2")))
                .andExpect(jsonPath("$[1].profilePicture", is("img2")))
                .andExpect(jsonPath("$[1].media[0]", is("url2")));

        Mockito.verify(reelsService, Mockito.times(1)).getAllReels();
        Mockito.verify(mediaService, Mockito.times(1)).getMediaByReelId(1);
        Mockito.verify(mediaService, Mockito.times(1)).getMediaByReelId(2);
    }

    @Test
    void testGetAllReels_ServiceReturnsNull() throws Exception {
        Mockito.when(reelsService.getAllReels()).thenReturn(null);

        mockMvc.perform(post("/reels/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        Mockito.verify(reelsService, Mockito.times(1)).getAllReels();
        Mockito.verify(mediaService, Mockito.times(0)).getMediaByReelId(anyInt());
    }

}