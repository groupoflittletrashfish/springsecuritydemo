package com.noname.springsecuritydemo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/17 9:32
 * @description ：
 * @modified By：
 * @version:
 */

@Service
public class MyServiceImpl implements MyService {
    @Override
    public boolean hasPermisssion(HttpServletRequest request, Authentication authentication) {
        //获取用户对象
        Object obj = authentication.getPrincipal();
        if (obj instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) obj;
            //获取用户所拥有的全部权限
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            //检查用户是否拥有当前URI的权限
            return authorities.contains(new SimpleGrantedAuthority(request.getRequestURI()));
        }
        return false;
    }
}
