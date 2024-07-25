package com.example.pet.parent.controller;

import com.example.pet.parent.model.Users;
import com.example.pet.parent.request.Follow.FollowIdRequest;
import com.example.pet.parent.service.FollowService;
import com.example.pet.parent.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Users> getPeopleYouMayKnow (@RequestBody FollowIdRequest followIdRequest) {
        return usersService.getPeopleYouMayKnow(followIdRequest.getUserId());
    }

}