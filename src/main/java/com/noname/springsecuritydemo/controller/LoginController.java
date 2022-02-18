package com.noname.springsecuritydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/16 14:05
 * @description ：
 * @modified By：
 * @version:
 */

@Controller
public class LoginController {

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/toMain")
    @PreAuthorize("hasRole('abc')")
    public String toMain() {
        return "redirect:main.html";
    }


    @RequestMapping("/toError")
    public String toError() {
        return "redirect:error.html";
    }


    @RequestMapping("demo")
    public String demo() {
        return "demo";
    }


    @RequestMapping("showLogin")
    public String showLogin() {
        return "login";
    }
}
