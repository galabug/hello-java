package com.gala.bug.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 设置spring生成多例对象，默认单例
@Scope(value="prototype")
@Component
public class UserService  {
    @Autowired
    public LoginService loginService;
    public void show() {
         System.out.println(this+"run show()");
    }
    public LoginService getLoginService() {
       return loginService;
    }
}
