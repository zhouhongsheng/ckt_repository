package com.sioeye.sidecar.controller;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sioeye.sidecar.config.CustomException;
import com.sioeye.sidecar.config.EnumHandle;
import com.sioeye.sidecar.model.User;
import com.sioeye.sidecar.service.intf.IUser;

@RestController
@RequestMapping(value = "/user")
@RefreshScope
public class UserController {

	@Autowired
	private IUser iUser;

	@RequestMapping(value = "/query_user", method = RequestMethod.POST)
	public String queryFace(@RequestBody Map<String, Object> map) {
		String result = "";
		try {
			Optional.ofNullable(map.get("id")).orElseThrow(() -> new CustomException(EnumHandle.PARAMS_INCORRECT));
			String id = map.get("id").toString();
			User user = iUser.queryFace(id);
			result = JSONObject.toJSONString(user);
		} catch (CustomException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
