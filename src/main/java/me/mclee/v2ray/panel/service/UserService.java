package me.mclee.v2ray.panel.service;

import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.entity.User;
import me.mclee.v2ray.panel.entity.model.UserModel;
import org.springframework.stereotype.Service;

/**
 * 用户
 */
public interface UserService {

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     * @throws AppException 查询用户失败
     */
    User queryUserById(int id) throws AppException;

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return 用户信息
     * @throws AppException 查询用户失败
     */
    User queryUserByName(String name) throws AppException;

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return 用户信息
     * @throws AppException 查询用户失败
     */
    UserModel queryUserModelByName(String name) throws AppException;

    /**
     * 创建用户
     * @param user 用户信息
     * @throws AppException 创建用户失败
     */
    void createUser(User user) throws AppException;
}
