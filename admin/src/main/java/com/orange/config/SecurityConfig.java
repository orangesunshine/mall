package com.orange.config;

import com.orange.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UmsAdminService umsAdminService;

    /**
     * 用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器；
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement()//基于token，不需要session
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**")
                .permitAll()
                .antMatchers("/admin/login", "/admin/register")//登录/注册 允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求回先进行一次options请求
                .permitAll()
                .anyRequest()
                .authenticated();

        //禁止缓存
        http.headers().cacheControl();
        //添加JWT filter
//        http.addFilterBefore()

        //添加自定义未授权和未登录 返回
//        http.
    }

    /**
     * 用于配置UserDetailsService及PasswordEncoder；
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    /**
     * SpringSecurity定义的用于对密码进行编码及比对的接口，目前使用的是BCryptPasswordEncoder；
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * SpringSecurity定义的核心接口，用于根据用户名获取用户信息，需要自行实现；
     *
     * @return
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            throw new UsernameNotFoundException("用户名或密码错误");
//        }
//    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
