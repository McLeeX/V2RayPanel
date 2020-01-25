package me.mclee.v2ray.panel.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailHandler extends ForwardAuthenticationFailureHandler {
    /**
     * @param forwardUrl forwardUrl
     */
    public LoginFailHandler(String forwardUrl) {
        super(forwardUrl);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);
    }
}
