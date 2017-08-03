package com.nickchen.service;

import com.nickchen.annotation.ServiceInterface;
import com.nickchen.annotation.ServiceMethod;
import com.nickchen.bean.Hotel;

import java.util.Map;

/**
 * @author nickChen
 * @create 2017-08-02 16:44.
 */
@ServiceInterface(protocol = "http", baseUrl = "localhost:8080")
public interface HotelService {
    @ServiceMethod("/user")
    Hotel getHotel(Map<String, Object> map);
}
