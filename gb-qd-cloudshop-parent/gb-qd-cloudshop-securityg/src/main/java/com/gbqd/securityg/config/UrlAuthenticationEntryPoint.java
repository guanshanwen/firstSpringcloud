package com.gbqd.securityg.config;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 用户未登录
 */
@Component
public class UrlAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        ResultCode response = new ResultCode();
        response.setStatus(ResultCodeStatus.FAIL);
        response.setMsg("用户未登录!");
        response.setNetStatus("000");
        httpServletResponse.getWriter().write(JsonUtil.GSON.toJson(response));
    }
}