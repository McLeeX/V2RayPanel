package me.mclee.v2ray.panel.security.service;

import java.util.List;

import me.mclee.v2ray.panel.entity.User;
import me.mclee.v2ray.panel.entity.UserExample;
import me.mclee.v2ray.panel.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample example = new UserExample();
        example.or().andNameEqualTo(username);
        List<User> userList = userMapper.selectByExample(example);
        User user = userList.stream().findFirst().orElseThrow(() -> new UsernameNotFoundException("用户名不存在：" + username));
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRole().toString());
        return userBuilder.build();
    }
}
