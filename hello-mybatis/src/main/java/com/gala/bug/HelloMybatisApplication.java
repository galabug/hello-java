package com.gala.bug;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gala.bug.dao")
public class HelloMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloMybatisApplication.class, args);
	}

}
