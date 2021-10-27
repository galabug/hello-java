package com.project;

import com.spring.BeanPostProcessor;
import com.spring.annotation.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class AopBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if ( "userService".equals(beanName)) {
            Object proxyInstance = Proxy.newProxyInstance(AopBeanPostProcessor.class.getClassLoader(),
                    bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 切面
                    Object result = null;
                    if ("test".equals(method.getName())) {
                        System.out.println("userService切面逻辑前");
                        result = method.invoke(bean, args);
                        System.out.println("userService切面逻辑后");
                    }else{
                        result = method.invoke(bean, args);
                    }
                    return result;
                }
            });
            return proxyInstance;
        }
        return bean;
    }
}
