package com.example.pet.parent.service;

import com.example.pet.parent.model.Follow;
import com.example.pet.parent.model.Users;
import com.example.pet.parent.repository.FollowRepository;
import com.example.pet.parent.service.impl.FollowServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FollowServiceTest {

    @Mock
    private FollowRepository followRepository;

    @InjectMocks
    private FollowServiceImpl followService;

    @Test
    void testGetPeopleYouMayKnow_ValidSuggestions() {
        Users user1 = new Users();
        user1.setUserId(1);

        Users user2 = new Users();
        user2.setUserId(2);

        Users user3 = new Users();
        user3.setUserId(3);

        Users user4 = new Users();
        user4.setUserId(4);

        Follow follow1 = new Follow();
        follow1.setFollower(user1);
        follow1.setFollowee(user2);

        Follow follow2 = new Follow();
        follow2.setFollower(user2);
        follow2.setFollowee(user3);

        Follow follow3 = new Follow();
        follow3.setFollower(user2);
        follow3.setFollowee(user4);

        Mockito.when(followRepository.findByFollowerUserId(1)).thenReturn(Collections.singletonList(follow1));
        Mockito.when(followRepository.findByFollowerUserId(2)).thenReturn(List.of(follow2, follow3));

        List<Integer> suggestions = followService.getPeopleYouMayKnow(1);

        assertEquals(2, suggestions.size());
        assertTrue(suggestions.contains(3));
        assertTrue(suggestions.contains(4));

        Mockito.verify(followRepository, Mockito.times(1)).findByFollowerUserId(1);
        Mockito.verify(followRepository, Mockito.times(1)).findByFollowerUserId(2);

    }

    @Test
    void testGetPeopleYouMayKnow_NoSuggestions() {
        Users user1 = new Users();
        user1.setUserId(1);

        Mockito.when(followRepository.findByFollowerUserId(1)).thenReturn(Collections.emptyList());

        List<Integer> suggestions = followService.getPeopleYouMayKnow(1);

        assertTrue(suggestions.isEmpty());

        Mockito.verify(followRepository, Mockito.times(1)).findByFollowerUserId(1);
    }

    @Test
    void testGetPeopleYouMayKnow_SelfFollowIsExcluded() {
        Users user1 = new Users();
        user1.setUserId(1);

        Users user2 = new Users();
        user2.setUserId(2);

        Users user3 = new Users();
        user3.setUserId(3);

        Follow follow1 = new Follow();
        follow1.setFollower(user1);
        follow1.setFollowee(user2);

        Follow follow2 = new Follow();
        follow2.setFollower(user2);
        follow2.setFollowee(user1);

        Follow follow3 = new Follow();
        follow3.setFollower(user2);
        follow3.setFollowee(user3);

        Mockito.when(followRepository.findByFollowerUserId(1)).thenReturn(Collections.singletonList(follow1));
        Mockito.when(followRepository.findByFollowerUserId(2)).thenReturn(List.of(follow2, follow3));

        List<Integer> suggestions = followService.getPeopleYouMayKnow(1);

        assertEquals(1, suggestions.size());
        assertTrue(suggestions.contains(3));
        assertFalse(suggestions.contains(1));       // Should not include self-follow

        Mockito.verify(followRepository, Mockito.times(1)).findByFollowerUserId(1);
        Mockito.verify(followRepository, Mockito.times(1)).findByFollowerUserId(2);
    }

    @Test
    void testGetPeopleYouMayKnow_NoOverlapInFollowees() {
        Users user1 = new Users();
        user1.setUserId(1);

        Users user2 = new Users();
        user2.setUserId(2);

        Users user3 = new Users();
        user3.setUserId(3);

        Users user4 = new Users();
        user4.setUserId(4);

        Follow follow1 = new Follow();
        follow1.setFollower(user1);
        follow1.setFollowee(user2);

        Follow follow2 = new Follow();
        follow2.setFollower(user2);
        follow2.setFollowee(user3);

        Follow follow3 = new Follow();
        follow3.setFollower(user1);
        follow3.setFollowee(user4);

        Mockito.when(followRepository.findByFollowerUserId(1)).thenReturn(List.of(follow1, follow3));
        Mockito.when(followRepository.findByFollowerUserId(2)).thenReturn(Collections.singletonList(follow2));

        List<Integer> suggestions = followService.getPeopleYouMayKnow(1);

        assertEquals(1, suggestions.size());
        assertTrue(suggestions.contains(3));

        Mockito.verify(followRepository, Mockito.times(1)).findByFollowerUserId(1);
        Mockito.verify(followRepository, Mockito.times(1)).findByFollowerUserId(2);
    }

}
