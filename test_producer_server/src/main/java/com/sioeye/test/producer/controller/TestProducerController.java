package com.sioeye.test.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/test-producer")
public class TestProducerController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "producerIndexFallback", groupKey = "producer-index", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "2"),
					@HystrixProperty(name = "maxQueueSize", value = "-1"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "10") })
	public String producerIndex() {
		return "hello world, this test producer ";
	}

	public String producerIndexFallback() {
		return "indexFallback";
	}
}
