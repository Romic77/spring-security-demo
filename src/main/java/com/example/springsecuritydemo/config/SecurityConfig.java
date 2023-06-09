package com.example.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Romic
 * @date 2023/6/9
 * @description
 */
//开启方法级别的权限控制
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(registry -> {
            registry.requestMatchers("/").permitAll() //放过首页
                    .anyRequest().authenticated();  //其他都需要权限
        });

        //表单登录功能
        httpSecurity.formLogin(formLogin -> {
            //自定义登录页位置，并且所有人都可以访问
            formLogin.loginPage("/login").permitAll();
        });

        return httpSecurity.build();
    }


    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails zhangsan = User.withUsername("zhangsan")
                .password(passwordEncoder.encode("123456")).roles("admin").authorities("file_read", "file_write").build();

        UserDetails lisi = User.withUsername("lisi")
                .password(passwordEncoder.encode("123456")).roles("admin").authorities("file_read", "all").build();
        return new InMemoryUserDetailsManager(zhangsan, lisi);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
