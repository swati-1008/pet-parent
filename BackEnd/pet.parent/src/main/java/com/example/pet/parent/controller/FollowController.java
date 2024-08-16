package com.example.pet.parent.controller;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.request.Follow.FollowIdRequest;
import com.example.pet.parent.service.FollowService;
import com.example.pet.parent.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/suggestions")
    public ResponseEntity<List<Users>> getPeopleYouMayKnow (@Valid @RequestBody FollowIdRequest followIdRequest) {
        if (followIdRequest.getUserId() == null)
            return ResponseEntity.badRequest().build();
        List<Users> usersList = usersService.getPeopleYouMayKnow(followIdRequest.getUserId());
        if (usersList == null || usersList.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usersList);
    }

}