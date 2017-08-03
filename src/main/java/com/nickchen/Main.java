package com.nickchen;

import com.nickchen.bean.Hotel;
import com.nickchen.core.ServiceInterfaceInvocationHandler;
import com.nickchen.service.HotelService;
import com.nickchen.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

/**
 * @author nickChen
 * @create 2017-08-02 10:54.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.getUser());
        //new HashMap<String, Object>()
        HotelService hotelService = (HotelService) context.getBean("hotelService");
        System.out.println(hotelService.getHotel(new HashMap<String, Object>()));

    }


}
