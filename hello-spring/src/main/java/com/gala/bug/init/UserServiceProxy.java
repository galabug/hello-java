package com.gala.bug.init;

public class UserServiceProxy extends UserService {
    public UserService userService;
    public void show(){
//        before()
        userService.show();
//        afler()
    }
}
