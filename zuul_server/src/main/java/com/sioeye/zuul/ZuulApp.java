package com.sioeye.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 
 * @author zhouyou
 * @date 2017年10月11日
 * @desc zuul server
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulApp {
	public static void main(String[] args) {
		SpringApplication.run(ZuulApp.class, args);
		System.out.println("start zuul server success .");
	}
}
