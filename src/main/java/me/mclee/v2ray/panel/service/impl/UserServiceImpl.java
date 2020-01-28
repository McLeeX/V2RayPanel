package me.mclee.v2ray.panel.service.impl;

import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.entity.User;
import me.mclee.v2ray.panel.entity.UserExample;
import me.mclee.v2ray.panel.entity.model.UserModel;
import me.mclee.v2ray.panel.mapper.UserMapper;
import me.mclee.v2ray.panel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     * @throws AppException 查询用户失败
     */
    @Override
    public User queryUserById(int id) throws AppException {
        User user = userMapper.selectByPrimaryKey(id);
        return Optional.ofNullable(user).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     * @throws AppException 查询用户失败
     */
    @Override
    public User queryUserByName(String username) throws AppException {
        UserExample example = new UserExample();
        example.or().andNameEqualTo(username);
        List<User> userList = userMapper.selectByExample(example);
        return userList.stream().findFirst().orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return 用户信息
     * @throws AppException 查询用户失败
     */
    @Override
    public UserModel queryUserModelByName(String name) throws AppException {
        User user = queryUserByName(name);
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setEmail(user.getEmail());
        userModel.setName(user.getName());
        userModel.setRole(user.getRole());
        return userModel;
    }

    /**
     * 创建用户
     *
     * @param user 用户信息
     */
    @Override
    public void createUser(User user) throws AppException {
        String name = user.getName();
        // 名称不能重复
        UserExample example = new UserExample();
        example.or().andNameEqualTo(name);
        if (userMapper.countByExample(example) > 0) {
            throw new AppException(ErrorCode.USER_NAME_DUPLICATED);
        }
        // 密码
        String password = user.getPassword();
        password = passwordEncoder.encode(password);
        user.setPassword(password);

        String email = user.getEmail();
        Integer role = user.getRole();

        user.setId(null);
        userMapper.insert(user);
    }
}
