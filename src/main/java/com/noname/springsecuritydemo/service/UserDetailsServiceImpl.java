package com.noname.springsecuritydemo.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/16 14:49
 * @description ：
 * @modified By：
 * @version:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder pw;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        String password = pw.encode("123");
        //※※※※※※※※※   为用户添加两个/insert和/delete的权限    ※※※※※※※※※※※※※※
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_abc,/insert,/delete"));
    }
}
