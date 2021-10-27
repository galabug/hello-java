package com.gala.bug.init;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

//BeanPostProcessor 所有spring管理对象的初始化前 初始化后都会进入下面两个对应方法
@Component
public class UserBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            System.out.println("User::UserBeanPostProcessor run postProcessBeforeInitialization()");
        }
        return bean;
    }
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            System.out.println("User::UserBeanPostProcessor run postProcessAfterInitialization()");
        }
        return bean;
    }
}
