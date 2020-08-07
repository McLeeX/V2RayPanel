package me.mclee.v2ray.panel.entity.convertor;

import me.mclee.v2ray.panel.entity.User;
import me.mclee.v2ray.panel.entity.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserUserModelConverter implements Converter<User, UserModel> {

    /**
     * 转换对象
     *
     * @param source 源对象
     * @return 转换结果
     */
    @Override
    public UserModel doForward(User source) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(source, userModel, "password");
        return userModel;
    }

    /**
     * 反向转换
     *
     * @param target 结果对象
     * @return 反向转换结果
     */
    @Override
    public User doBackward(UserModel target) {
        User user = new User();
        BeanUtils.copyProperties(target, user);
        return user;
    }
}
