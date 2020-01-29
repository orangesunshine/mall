package com.orange.controller;

import com.orange.model.User;
import com.orange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/all")
    List<User> listAllUser() {
        return userService.listAll();
    }

    @RequestMapping("/insert")
    int insertUser(@RequestBody User user) {
        return userService.insert(user);
    }

    @RequestMapping("/delete/{id}")
    int deleteUser(@PathVariable("id") long id) {
        return userService.delete(id);
    }

    @RequestMapping("/update/{id}")
    int updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @RequestMapping("/list/{id}")
    User listUser(@PathVariable("id") long id) {
        return userService.list(id);
    }
}
