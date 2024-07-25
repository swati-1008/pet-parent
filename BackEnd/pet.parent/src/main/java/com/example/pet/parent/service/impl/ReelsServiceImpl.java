package com.example.pet.parent.service.impl;

import com.example.pet.parent.model.Reels;
import com.example.pet.parent.repository.ReelsRepository;
import com.example.pet.parent.service.ReelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;

    @Override
    public List<Reels> getAllReels () {
        return reelsRepository.findAll();
    }

}
