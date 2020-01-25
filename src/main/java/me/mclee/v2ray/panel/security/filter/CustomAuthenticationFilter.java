package me.mclee.v2ray.panel.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.mclee.v2ray.panel.entity.security.AuthenticationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<AuthenticationBean> threadLocal = new ThreadLocal<>();

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    public CustomAuthenticationFilter() {
        super();
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/doLogin", "POST"));
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = this.getAuthenticationBean(request).getPassword();
        if (!StringUtils.isEmpty(password)) {
            return password;
        }
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = this.getAuthenticationBean(request).getUsername();
        if (!StringUtils.isEmpty(username)) {
            return username;
        }
        return super.obtainUsername(request);
    }

    private AuthenticationBean getAuthenticationBean(HttpServletRequest request) {
        AuthenticationBean authenticationBean = threadLocal.get();
        if (authenticationBean == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try (InputStream is = request.getInputStream()) {
                authenticationBean = objectMapper.readValue(is, AuthenticationBean.class);
            } catch (IOException ignored) {
            }
            threadLocal.set(authenticationBean);
        }

        return authenticationBean;
    }
}
