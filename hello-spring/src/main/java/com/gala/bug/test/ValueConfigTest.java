package com.gala.bug.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@PropertySource("classpath:test.properties")
@Component
public class ValueConfigTest {

    @Value("${book.name}")
    public String bookName;

    @Value("zhangsan")
    public String name;

    @Value("#{systemProperties['os.name']}")
    public String osName;

    @Value("#{T(java.lang.Math).random()*100}")
    public String randomNum;

    @Value("#{userService.name}")
    public String demoClassName;

    @Value("classpath:value.txt")
    public String msg;

    @Override
    public String toString() {
        return "ValueConfigTest{" +
                "bookName='" + bookName + '\'' +
                ", name='" + name + '\'' +
                ", osName='" + osName + '\'' +
                ", randomNum='" + randomNum + '\'' +
                ", demoClassName='" + demoClassName + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
