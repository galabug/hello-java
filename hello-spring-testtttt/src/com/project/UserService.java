package com.project;

import com.spring.annotation.Autowired;
import com.spring.annotation.Component;

@Component
public class UserService {

    @Autowired
    OrderService orderService;

    void  test(){
        System.out.println("userService test running");
    }
    void  show(){
        System.out.println("userService show orderService:"+orderService);
    }
}
