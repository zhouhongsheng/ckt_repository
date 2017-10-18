package com.sioeye.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin.server.EnableZipkinServer;

/**
 * 
 * @author zhouyou
 * @date 2017年10月18日
 * @desc zipkin server
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class ZipkinApp {
	public static void main(String[] args) {
		SpringApplication.run(ZipkinApp.class, args);
		System.out.println("zipkin server start successfully . ");
	}
}
