package com.noname.springsecuritydemo.handle;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/16 16:11
 * @description ：
 * @modified By：
 * @version:
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String url;

    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //登录人的信息
        User user = (User) authentication.getPrincipal();
        //账号
        System.out.println(user.getUsername());
        //注意：密码一定是null,主要是为了安全起见
        System.out.println(user.getPassword());
        //账号所拥有的权限，在认证通过时候已经加入了
        System.out.println(user.getAuthorities());
        response.sendRedirect(url);
    }
}
