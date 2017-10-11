package com.sioeye.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @author zhouyou
 * @date 2017年10月11日
 * @desc eureka server
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaApp.class, args);
		System.out.println("start eureka server success .");
	}
}