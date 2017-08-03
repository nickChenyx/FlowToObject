package com.nickchen.service;

import com.nickchen.annotation.ServiceInterface;
import com.nickchen.annotation.ServiceMethod;
import com.nickchen.bean.User;

import java.util.Map;

/**
 * @author nickChen
 * @create 2017-08-02 11:06.
 */
@ServiceInterface(protocol = "http", baseUrl = "localhost:8080")
public interface UserService {
    @ServiceMethod("/user")
    User getUser();
//    Map<String, Object> map
}
