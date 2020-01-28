package me.mclee.v2ray.panel.controller;

import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ResponseData;
import me.mclee.v2ray.panel.entity.User;
import me.mclee.v2ray.panel.entity.model.UserModel;
import me.mclee.v2ray.panel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

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

    /**
     * 获取当前用户信息
     *
     * @return 当前用户信息
     * @throws AppException 用户信息查询失败
     */
    @GetMapping("/auth/me")
    private ResponseData<UserModel> myInformation() throws AppException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserModel userModel = userService.queryUserModelByName(username);
        return ResponseData.success(userModel);
    }
}
