package com.nickchen;

import com.nickchen.bean.*;
import com.nickchen.service.HotelService;
import com.nickchen.service.UserService;
import com.nickchen.core.ServiceInterfaceInvocationHanlder;
import com.nickchen.util.SpringContextUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author nickChen
 * @create 2017-08-02 10:54.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.getUser("123"));
        HotelService hotelService = (HotelService) context.getBean("hotelService");
        System.out.println(hotelService.getHotel("1"));
    }


}
