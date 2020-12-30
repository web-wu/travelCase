package com.innosen.travel.service;

import com.innosen.travel.entity.User;

public interface UserService {
    boolean register(User user);

    boolean active(String code);

    User login(User user);
}

