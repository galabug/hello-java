package com.project;

import com.spring.annotation.Autowired;
import com.spring.annotation.Component;

@Component
public class UserService  implements UserInterface{
    @Autowired
    public OrderService orderService;
    public void  test(){
        System.out.println("userService test running = " + orderService);
    }
}
