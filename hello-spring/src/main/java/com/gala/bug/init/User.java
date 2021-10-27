package com.gala.bug.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
public class User implements InitializingBean {
    public String name;
    public User() {
        System.out.println("User::构造函数 run");
    }
    // jdk提供对象初始化前注释的Postcontruct
    @PostConstruct
    void test(){
        System.out.println("User::PostConstruct run test()");
    }
    //初始化
    @Override // InitializingBean
    public void afterPropertiesSet() throws Exception {
        System.out.println("User::InitializingBean run afterPropertiesSet()");
    }
}
