package com.sioeye.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 
 * @author zhouyou
 * @date 2017年10月11日
 * @desc 测试项目
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class TestApp {
	public static void main(String[] args) {
		SpringApplication.run(TestApp.class, args);
		System.out.println("test server start success .");
	}
}
