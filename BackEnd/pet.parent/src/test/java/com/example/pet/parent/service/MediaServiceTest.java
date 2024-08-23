package com.example.pet.parent.service;

import com.example.pet.parent.model.Media;
import com.example.pet.parent.repository.MediaRepository;
import com.example.pet.parent.service.impl.MediaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class MediaServiceTest {

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private MediaServiceImpl mediaService;

    @Test
    void testGetMediaByReelId_ValidReelId_ReturnsMediaList() {
        Media media1 = new Media();
        media1.setId(1);
        media1.setContent("http://example.com/media1");

        Media media2 = new Media();
        media2.setId(2);
        media2.setContent("http://example.com/media2");
        int reelId = 1;

        List<Media> expectedMediaList = List.of(media1, media2);

        Mockito.when(mediaRepository.findByReelsId(reelId)).thenReturn(expectedMediaList);

        List<Media> actualMediaList = mediaService.getMediaByReelId(reelId);

        assertEquals(2, actualMediaList.size());
        assertTrue(actualMediaList.contains(media1));
        assertTrue(actualMediaList.contains(media2));

        Mockito.verify(mediaRepository, Mockito.times(1)).findByReelsId(reelId);
    }

    @Test
    void testGetMediaByReelId_ValidReelId_ReturnsEmptyList() {
        int reelId = 1;

        Mockito.when(mediaRepository.findByReelsId(reelId)).thenReturn(Collections.emptyList());

        List<Media> actualMediaList = mediaService.getMediaByReelId(reelId);

        assertTrue(actualMediaList.isEmpty());

        Mockito.verify(mediaRepository, Mockito.times(1)).findByReelsId(reelId);
    }

    @Test
    void testGetMediaByReelId_InvalidReelId_ReturnsEmptyList() {
        int reelId = 999;

        Mockito.when(mediaRepository.findByReelsId(reelId)).thenReturn(Collections.emptyList());

        List<Media> actualMediaList = mediaService.getMediaByReelId(reelId);

        assertTrue(actualMediaList.isEmpty());

        Mockito.verify(mediaRepository, Mockito.times(1)).findByReelsId(reelId);
    }

    @Test
    void testGetMediaByReelId_RepositoryThrowsException() {
        int reelId = 1;

        Mockito.when(mediaRepository.findByReelsId(reelId)).thenThrow(new RuntimeException("Database error"));

        try {
            mediaService.getMediaByReelId(reelId);
        } catch (Exception e) {
            assertEquals("Database error", e.getMessage());
        }

        Mockito.verify(mediaRepository, Mockito.times(1)).findByReelsId(reelId);
    }

}
