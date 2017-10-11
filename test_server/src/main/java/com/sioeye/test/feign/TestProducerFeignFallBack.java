package com.sioeye.test.feign;

import org.springframework.stereotype.Component;

@Component
public class TestProducerFeignFallBack implements ITestProducerFeign {

	@Override
	public String index() {
		return null;
	}
}
