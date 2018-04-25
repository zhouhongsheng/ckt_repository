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

import com.sioeye.log.SioeyeLog;
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
    private static final Log log = LogFactory.getLog(UserController.class);

    @RequestMapping(value = "/query_user", method = RequestMethod.POST)
    public String queryFace(@RequestBody Map<String, Object> map) {
        String result = "";
        try {
            Optional.ofNullable(map.get("id")).orElseThrow(() -> new CustomException(EnumHandle.PARAMS_INCORRECT));
            String id = map.get("id").toString();
            User user = iUser.queryFace(id);
            result = SioeyeLog.packageSuccessLog(log, user);
        } catch (CustomException e) {
            result = SioeyeLog.packageExceptionLog(log, e);
        } catch (Exception e) {
            SioeyeLog.packageErrorLog(log, e.toString());
        }
        return result;
    }
}
