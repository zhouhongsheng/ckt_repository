package com.sioeye.sidecar.service.feign;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sidecar-user-server")
public interface IUserServerFeign {

    @RequestMapping(value = "/user/query_user", method = RequestMethod.POST)
    public String findByUid(@RequestBody Map<String, String> map);
}
