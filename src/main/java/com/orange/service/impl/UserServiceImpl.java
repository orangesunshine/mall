package com.orange.service.impl;

import com.orange.mapper.UserMapper;
import com.orange.model.User;
import com.orange.model.UserExample;
import com.orange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> listAll() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int delete(long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(long id, User user) {
        user.setId(id);
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User list(long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
