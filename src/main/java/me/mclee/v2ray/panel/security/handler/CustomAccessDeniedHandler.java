package me.mclee.v2ray.panel.security.handler;

import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.ResponseData;
import me.mclee.v2ray.panel.common.utils.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseData<Serializable> responseData = ResponseData.fail(ErrorCode.AUTHENTICATION_ERROR);
        String responseBody = JsonUtil.obj2String(responseData);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(responseBody);
    }
}
