package com.example.pet.parent.controller;

import com.example.pet.parent.model.Reels;
import com.example.pet.parent.model.Media;
import com.example.pet.parent.response.Reels.ReelsResponse;
import com.example.pet.parent.service.MediaService;
import com.example.pet.parent.service.ReelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reels")
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private MediaService mediaService;

    @PostMapping("/all")
    public List<ReelsResponse> getAllReels () {
//        return reelsService.getAllReels();
        List<Reels> reels = reelsService.getAllReels();
        List<ReelsResponse> reelsResponses = new ArrayList<>();

        for (Reels reel : reels) {
            List<String> mediaUrls = mediaService.getMediaByReelId(reel.getId())
                    .stream()
                    .map(Media::getContent)
                    .toList();

            ReelsResponse reelResponse = new ReelsResponse(
                    reel.getId(),
                    reel.getUsername(),
                    reel.getProfilePicture(),
                    mediaUrls
            );

            reelsResponses.add(reelResponse);
        }

        return reelsResponses;

    }

}
