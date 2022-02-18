package com.noname.springsecuritydemo.config;

import com.noname.springsecuritydemo.handle.MyAcessDeniedHandler;
import com.noname.springsecuritydemo.handle.MyAuthenticationFailtureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/16 14:47
 * @description ：
 * @modified By：
 * @version:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAcessDeniedHandler myAcessDeniedHandler;
    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;
    @Resource
    private DataSource dataSource;

    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .usernameParameter("username123")
                .passwordParameter("password123")
                .loginProcessingUrl("/login")
                // ※※※※※※※※※※※     登录的页面也需要更改成showLogin这个地址    ※※※※※※※※※※
                .loginPage("/showLogin")
                .successForwardUrl("/toMain")
                .failureHandler(new MyAuthenticationFailtureHandler("/error.html"));
        http.authorizeRequests()
                //※※※※※※※※※※※      不要忘记此处也需要放行      ※※※※※※※※※※
                .antMatchers("/showLogin").permitAll()
                .antMatchers("/error.html").permitAll()
                .regexMatchers(".+[.png]").permitAll()
                .anyRequest().authenticated();
        //※※※※※※※※   这个csrf必须去掉了    ※※※※※※※※※※※※
        //http.csrf().disable();

        http.exceptionHandling().accessDeniedHandler(myAcessDeniedHandler);

        http.rememberMe()
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(20)
                .tokenRepository(getPersistentTokenRepository());

        http.logout()
                .logoutSuccessUrl("/login.html");
    }


    @Bean
    public PersistentTokenRepository getPersistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}
