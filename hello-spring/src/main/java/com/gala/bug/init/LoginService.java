package com.gala.bug.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 设置对象为多例，默认是单例
//@Scope(value="prototype")

// 1.1 控制反转
// 注册到spring容器中，本类托管spring容器生成对象
@Component
public class LoginService {
    //1.2 依赖注入
    // spring容器生成LoginService对象时，
    // 给user属性自动注入User对象
//    @Autowired
//    public User user;
}
