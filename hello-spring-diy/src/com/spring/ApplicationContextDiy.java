package com.spring;

import com.spring.annotation.Autowired;
import com.spring.annotation.Component;
import com.spring.annotation.ComponentScan;
import com.spring.annotation.Scope;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContextDiy {
    // 配置类
    private Class configClass;
    // BeanDefinition 集合
    private Map<String,BeanDefinition> beanDefinitionMap= new HashMap<>();
    // 生成的单例对象集合
    private Map<String, Object> singletonObjects = new HashMap<>();
    // BeanPostProcessor对象集合
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    // 1.构造函数
    public ApplicationContextDiy(Class configClass) {
        // 获取配置类
        this.configClass = configClass;
        // 扫描加载class，把class封装成BeanDefinition
        scan(configClass);
        // 加载单例非懒加载的对象
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    // 2.扫描指定目录，加载扫描到的class，并把记录缓存class信息封装成BeanDefinition
    public void scan(Class configClass){
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            // 获取  ComponentScan配置的扫描目录路径
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = componentScanAnnotation.value();
            path = path.replace(".", "/");
            // 读取扫描目录里的文件
            ClassLoader classLoader = ApplicationContextDiy.class.getClassLoader();
            URL resource = classLoader.getResource(path);

            File file = new File(resource.getFile());
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
//                    加载class
                    String absolutePath = f.getAbsolutePath();
                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replace("\\", ".");
                    try {
                        Class<?> clazz = classLoader.loadClass(absolutePath);
//                        如果class由Component注解 缓存class信息，等待后面实例化
                        if (clazz.isAnnotationPresent(Component.class)) {
                            Component componentAnnotation = clazz.getAnnotation(Component.class);
                            String beanName = componentAnnotation.value();
                            if ("".equals(beanName)) {
                                beanName = Introspector.decapitalize(clazz.getSimpleName());
                            }
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClassName(clazz);
                            if (clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                String value = scopeAnnotation.value();
                                beanDefinition.setScope(value);
                            } else {
                                beanDefinition.setScope("singleton");
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }

                        if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                            BeanPostProcessor instance = (BeanPostProcessor) clazz.getConstructor().newInstance();
                            beanPostProcessorList.add(instance);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


    // 3.简略创建对象，实际用工厂模式创建
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClassName();
        Object instance = null;
        try {
            instance = clazz.getConstructor().newInstance();
//          依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    field.set(instance, getBean(field.getName()));
                }
            }
            // 执行 BeanPostProcessor 初始化前置方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }
            //执行 InitializingBean 初始化方法
            if (instance instanceof InitializingBean) {
                ((InitializingBean)instance).afterPropertiesSet();
            }
            // 执行 BeanPostProcessor 初始化后置方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    // 4.获取bean对象
    public Object getBean(String beanName){
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException();
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        // 单例直接从缓存里取实例bean对象
        if (beanDefinition.getScope().equals("singleton")) {
            Object singletonBean = singletonObjects.get(beanName);
            if (singletonBean == null) {
                singletonBean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, singletonBean);
            }
            return singletonBean;
        } else {
            // 原型多例新创建实例bean对象
            Object prototypeBean = createBean(beanName, beanDefinition);
            return prototypeBean;
        }
    }



}
