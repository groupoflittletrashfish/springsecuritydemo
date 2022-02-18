package com.noname.springsecuritydemo.handle;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/16 16:26
 * @description ：
 * @modified By：
 * @version:
 */
public class MyAuthenticationFailtureHandler implements AuthenticationFailureHandler {

    private String url;

    public MyAuthenticationFailtureHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect(url);
    }
}
