package com.noname.springsecuritydemo.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyService {

    boolean hasPermisssion(HttpServletRequest request, Authentication authentication);
}
