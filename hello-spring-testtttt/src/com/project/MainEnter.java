package com.project;

import com.spring.ApplicationContextDiy;

public class MainEnter {
    public static void main(String[] args) {
        ApplicationContextDiy applicationContext = new ApplicationContextDiy(AppConfig.class);
        UserService userService =  (UserService)applicationContext.getBean("userService");
        System.out.println(userService);

        userService.show();

    }
}
