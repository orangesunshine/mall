package com.orange.service.impl;

import com.github.pagehelper.PageHelper;
import com.orange.mapper.UserMapper;
import com.orange.model.User;
import com.orange.model.UserExample;
import com.orange.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired(required = false)
    UserMapper userMapper;


    @Override
    public List<User> page(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectByExample(new UserExample());
    }

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
