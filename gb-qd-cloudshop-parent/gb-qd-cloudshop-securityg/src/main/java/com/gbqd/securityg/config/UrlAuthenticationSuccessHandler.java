package com.gbqd.securityg.config;


import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
/**
 * 登录成功
 */
@Component
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  /*  @Resource
    private UserService userService;*/

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setCharacterEncoding("UTF-8");
        ResultCode response = new ResultCode();
        response.setStatus(ResultCodeStatus.SUCCESS);
        response.setMsg("登录成功!");
        response.setNetStatus("200");
        String username = (String) authentication.getPrincipal(); //表单输入的用户名
        String password = (String) authentication.getCredentials(); //表单输入的密码
      /*  Set<Menu> menus = userService.getMenusByUsername(username);
        response.setResult(menus);*/

        httpServletResponse.getWriter().write(JsonUtil.GSON.toJson(response));
    }
}
