package com.sioeye.test.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-producer")
public class TestProducerController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "hello world, this test producer ";
	}
}
