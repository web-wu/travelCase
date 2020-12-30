package com.innosen.travel.dao;

import com.innosen.travel.entity.User;

public interface UserDao {
    boolean register(User user);

    User active(String code);

    int changeStatus(String code);

    User loggin(User user);
}
