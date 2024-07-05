package com.example.pet.parent.service;

import com.example.pet.parent.model.Media;

import java.util.List;

public interface MediaService {
    List<Media> getMediaByReelId (int reelId);
}
