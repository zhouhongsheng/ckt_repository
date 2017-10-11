package com.sioeye.turbine;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 
 * @author zhouyou
 * @date 2017年10月11日
 * @desc
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTurbine
@EnableHystrixDashboard
public class TurbineApp {
	public static void main(String[] args) {
		new SpringApplicationBuilder(TurbineApp.class).web(true).run(args);
		System.out.println("turbine server started successfully .");
	}
}
