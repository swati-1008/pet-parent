package com.example.pet.parent.service.impl;

import com.example.pet.parent.model.Follow;
import com.example.pet.parent.repository.FollowRepository;
import com.example.pet.parent.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Override
    public List<Integer> getPeopleYouMayKnow (Integer userId) {
        List<Follow> followedByUser = followRepository.findByFollowerUserId(userId);
        Set<Integer> followeeIds = followedByUser.stream()
                .map(f -> f.getFollowee().getUserId())
                .collect(Collectors.toSet());
        Set<Integer> suggestions = new HashSet<>();
        for (Integer followeeId : followeeIds) {
            List<Follow> followedByFollowees = followRepository.findByFollowerUserId(followeeId);
            suggestions.addAll(followedByFollowees.stream()
                    .map(f -> f.getFollowee().getUserId())
                    .collect(Collectors.toSet()));
        }
        suggestions.remove(userId);
        suggestions.removeAll(followeeIds);
        return new ArrayList<>(suggestions);
    }

}