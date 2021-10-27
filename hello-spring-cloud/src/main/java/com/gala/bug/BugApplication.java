package com.gala.bug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@EnableConfigurationProperties(DruidConfig.class)
//@MapperScan("com.gala.bug.dao")
//扫描@WebFilter， @WebListener， @WebServlet
@ServletComponentScan(basePackages = {"com.gala.bug"})
//@SpringBootApplication(scanBasePackages = {"com.gala.bug"})
@SpringBootApplication//扫描包下面的注解并把类实例化加入到 spring 容器中
public class BugApplication {
	public static void main(String[] args) {
		SpringApplication.run(BugApplication.class, args);
	}
}
