package com.sioeye.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author zhouyou
 * @date 2017年10月11日
 * @desc 测试controller
 */
@RestController
public class TestController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "hello world ";
	}
}
