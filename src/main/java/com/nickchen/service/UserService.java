package com.nickchen.service;

import com.nickchen.annotation.ServiceInterface;
import com.nickchen.annotation.ServiceMethod;
import com.nickchen.bean.User;

/**
 * @author nickChen
 * @create 2017-08-02 11:06.
 */
@ServiceInterface(protocol = "http", baseUrl = "/c")
public interface UserService {
    @ServiceMethod("/43bddff215e8")
    User getUser(String name);
}
