package com.sioeye.test.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 
 * @author zhouyou
 * @date 2017年10月11日
 * @desc 测试服务提供者
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class TestProducerApp {
	public static void main(String[] args) {
		SpringApplication.run(TestProducerApp.class, args);
		System.out.println("test producer server start successfully .");
	}
}