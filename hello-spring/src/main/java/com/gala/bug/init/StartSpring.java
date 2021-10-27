package com.gala.bug.init;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// UserService.class->无参构造->普通对想->依赖注入->初始化前(@PostConstruct)->初始化(InitializingBean)->初始化后（aop）->代理对象->bean
// 2.1定义spring要扫描管理对象的范围
@EnableAspectJAutoProxy//开启切片功能
@ComponentScan("com.gala.bug.init")
public class StartSpring {
    public static void main(String[] args) {
//      2.2 创建spring容器，容器初始化
        ApplicationContext context = new
                AnnotationConfigApplicationContext(StartSpring.class);
        UserService userService = (UserService)context.getBean("userService");
//      对象有切片时，spirng容器返回userService的代理对象，不是这个类的原对象。
        userService.show();
//        代理对象的属性值没值

        System.out.println("userService.loginService = "+userService.loginService);
//      代理的方法才能通过原对象拿到元对象的属性值
        System.out.println("userService.getLoginService() = "+userService.getLoginService());

//      2.3从spring容器中获取LoginService对象
//        LoginService springBean = (LoginService)context.getBean("loginService");
////      此时对象中user属性已经自动注入User对象
//        System.out.println("springBean = " + springBean.user);

//      2.4 我们自己在程序里new LoginService
//      user属性需要我们自己在代码里new User对象赋值，否则为null
//        LoginService newBean = new LoginService();
//        System.out.println("newBean = " + newBean.user);
    }
}
