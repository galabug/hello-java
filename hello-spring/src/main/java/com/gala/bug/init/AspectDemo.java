package com.gala.bug.init;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class AspectDemo {
//    @Before("execution(* com.gala.bug.init.UserService.show(..))")
    public void beforeMethod(){
        System.out.println("AspectDemo Before");
    }

    @Around("execution(* com.gala.bug.init.UserService.show(..))")
    public Object  arroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        System.out.println("AspectDemo Around Before");
        Object result = joinPoint.proceed(args);
        System.out.println("AspectDemo Around After");
        //如果这里不返回result，则目标对象实际返回值会被置为null
        return result;
    }

//    @After("execution(* com.gala.bug.init.UserService.show(..))")
    public void afterMethod(){
        System.out.println("AspectDemo After");
    }
}
