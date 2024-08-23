package com.example.pet.parent.service;

import com.example.pet.parent.model.Reels;
import com.example.pet.parent.repository.ReelsRepository;
import com.example.pet.parent.service.impl.ReelsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ReelsServiceTest {

    @Mock
    private ReelsRepository reelsRepository;

    @InjectMocks
    private ReelsServiceImpl reelsService;

    @Test
    void testGetAllReels_ReturnsReelsList() {
        Reels reel1 = new Reels(1, "user1", "img1", null);
        Reels reel2 = new Reels(2, "user2", "img2", null);
        List<Reels> reelsList = List.of(reel1, reel2);

        Mockito.when(reelsRepository.findAll()).thenReturn(reelsList);

        List<Reels> fetchedReels = reelsService.getAllReels();

        assertEquals(2, fetchedReels.size());
        assertEquals("user1", fetchedReels.get(0).getUsername());
        assertEquals("img2", fetchedReels.get(1).getProfilePicture());

        Mockito.verify(reelsRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetAllReels_WhenNoReelsFound_ReturnsEmptyList() {
        Mockito.when(reelsRepository.findAll()).thenReturn(new ArrayList<>());

        List<Reels> fetchedReels = reelsService.getAllReels();

        assertTrue(fetchedReels.isEmpty());

        Mockito.verify(reelsRepository, Mockito.times(1)).findAll();
    }

}
