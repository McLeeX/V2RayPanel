package me.mclee.v2ray.panel.controller;

import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.ResponseData;
import me.mclee.v2ray.panel.entity.User;
import me.mclee.v2ray.panel.entity.security.AuthenticationBean;
import me.mclee.v2ray.panel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    /**
     * 登录成功处理
     *
     * @param authentication 登录信息
     * @return 成功消息
     */
    @PostMapping("/login/success")
    private ResponseData<Serializable> loginSuccess(@RequestBody AuthenticationBean authentication) {
        return ResponseData.success();
    }

    /**
     * 登录失败处理
     *
     * @param authentication 登录失败
     * @return 失败消息
     */
    @PostMapping("/login/failure")
    private ResponseData<Serializable> loginFailure(@RequestBody AuthenticationBean authentication) throws AppException {
        throw new AppException(ErrorCode.AUTHENTICATION_ERROR);
    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return ResponseData
     * @throws AppException 注册失败
     */
    @PostMapping("/signup")
    private ResponseData<Serializable> signUp(@RequestBody User user) throws AppException {
        userService.createUser(user);
        return ResponseData.success();
    }
}
