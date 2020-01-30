package com.orange.service;

import com.orange.model.User;

import java.util.List;

public interface UserService {
    List<User> listAll();

    int insert(User user);

    int delete(long id);

    int update(long id, User user);

    User list(long id);
}
