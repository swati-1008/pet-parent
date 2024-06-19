package com.example.pet.parent.request.Users;

import com.example.pet.parent.model.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEditRequest {
    private int userId;
    private Users users;
}
