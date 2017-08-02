package com.nickchen.service;

import com.nickchen.annotation.ServiceInterface;
import com.nickchen.annotation.ServiceMethod;
import com.nickchen.bean.Hotel;

/**
 * @author nickChen
 * @create 2017-08-02 16:44.
 */
@ServiceInterface(protocol = "http", baseUrl = "/c")
public interface HotelService {
    @ServiceMethod("/43bddff215e8")
    Hotel getHotel(String name);
}
