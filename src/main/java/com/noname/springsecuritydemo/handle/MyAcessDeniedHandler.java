package com.noname.springsecuritydemo.handle;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/17 9:08
 * @description ：
 * @modified By：
 * @version:
 */
@Component
public class MyAcessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //设置响应的状态码
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("Content-Type", "application-json");
        PrintWriter writer = response.getWriter();
        writer.write("{\"status\":\"error\",\"message\":\"权限不足，请联系管理员\"}");
        writer.flush();
    }
}
