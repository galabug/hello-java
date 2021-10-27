package com.project;

import com.spring.ApplicationContextDiy;
import com.spring.annotation.ComponentScan;

@ComponentScan("com.project")
public class MainEnter {
    public static void main(String[] args) {
        /**
         * 模拟Spring框架
         * 1.创建容器：简单实现基本的四个注解
         *     1.1 @ComponentScan 定义扫描的目录范围
         *     1.2 @Component 定义要被Spring管理的class
         *     1.3 @Autowired 定义spring管理的对象需要注入的依赖
         *     1.4 @Scope 定义对象是否单例
         * 2.扫描@ComponentScan定义的目录里的class文件，
         *   2.1把含@Component注解的class包装BeanDefinition，放入BeanDefinition集合
         *      BeanDefinition{className-类名，scope-是否单例}
         *   2.2 把BeanPostProcessor生成实例对象，放入BeanPostProcessor集合
         * 3.遍历BeanDefinition集合，把单例的对象初始化，并放入缓存
         *      3.1 创建对象
         *      3.1 执行 InitializingBean接口功能
         *      3.2 执行 BeanPostProcessor接口功能
         * 4.提供getBean方法，供使用
         * 5 通过BeanPostProcessor和JDK动态代理模拟AOP功能
         */
        ApplicationContextDiy applicationContext = new ApplicationContextDiy(MainEnter.class);

        UserInterface userProxy =  (UserInterface)applicationContext.getBean("userService");
        System.out.println(userProxy);
        userProxy.test();

    }
}
