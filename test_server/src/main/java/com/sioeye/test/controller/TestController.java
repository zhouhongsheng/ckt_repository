package com.sioeye.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sioeye.test.feign.ITestProducerFeign;

/**
 * 
 * @author zhouyou
 * @date 2017年10月11日
 * @desc 测试controller
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private ITestProducerFeign iTestProducerFeign;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "indexFallback", groupKey = "index", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "2"),
					@HystrixProperty(name = "maxQueueSize", value = "-1"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "10") })
	public String index() {
		return iTestProducerFeign.index();
	}

	public String indexFallback() {
		return "indexFallback";
	}
}
