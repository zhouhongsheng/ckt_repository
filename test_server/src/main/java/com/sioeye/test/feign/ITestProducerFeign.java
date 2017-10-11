package com.sioeye.test.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "test-producer-server", fallback = TestProducerFeignFallBack.class)
public interface ITestProducerFeign {

	@RequestMapping(method = RequestMethod.GET, value = "/test-producer/index")
	public String index();
}
