package com.sioeye.sidecar.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.sioeye.sidecar.model.User;
import com.sioeye.sidecar.service.feign.IUserServerFeign;
import com.sioeye.sidecar.service.intf.IUser;

@Service
public class UserImpl implements IUser {

    @Autowired
    private IUserServerFeign iUserServerFeign;

    @Override
    public User queryFace(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        String faceStr = iUserServerFeign.findByUid(map);
        JSONObject jsonFace = JSONObject.parseObject(faceStr);
        User user = new User();
        user.setAddr(jsonFace.getString("addr"));
        user.setCourseId(jsonFace.getLong("courseId"));
        user.setDesc(jsonFace.getString("desc"));
        user.setId(jsonFace.getLong("id"));
        user.setName(jsonFace.getString("name"));
        user.setTeacherId(jsonFace.getLong("teacherId"));
        return user;
    }
}
