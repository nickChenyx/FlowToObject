package com.nickchen.service.impl;

import com.nickchen.bean.User;
import com.nickchen.service.UserService;

/**
 * @author nickChen
 * @create 2017-08-02 11:07.
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(String name) {
        System.out.println("getUser");
        return new User(name);
    }
}
