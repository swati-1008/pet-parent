package com.example.pet.parent.service.impl;

import com.example.pet.parent.model.Media;
import com.example.pet.parent.repository.MediaRepository;
import com.example.pet.parent.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public List<Media> getMediaByReelId (int reelId) {
        return mediaRepository.findByReelsId(reelId);
    }

}
