package com.example.springsecuritydemo.service.impl;

import com.example.springsecuritydemo.domain.AdminUserDetails;
import com.example.springsecuritydemo.domain.UmsResource;
import com.example.springsecuritydemo.service.UmsAdminService;
import com.example.springsecuritydemo.utils.JwtTokenUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Romic
 * @date 2023/6/10
 * @description
 */
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    /**
     * 存放默认用户信息
     */
    private List<AdminUserDetails> adminUserDetailsList = new ArrayList<>();

    /**
     * 存放默认资源信息
     */
    private List<UmsResource> resourceList = new ArrayList<>();

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() {
        adminUserDetailsList.add(AdminUserDetails.builder().username("admin").password(passwordEncoder.encode("123456"))
                .authorityList(List.of("1:brand:create\",\"2:brand:update\",\"3:brand:delete\",\"4:brand:list\",\"5:brand:listAll"))
                .build());

        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("macro")
                .password(passwordEncoder.encode("123456"))
                .authorityList(List.of("5:brand:listAll"))
                .build());

        resourceList.add(UmsResource.builder()
                .id(1L)
                .name("brand:create")
                .url("/brand/create")
                .build());
        resourceList.add(UmsResource.builder()
                .id(2L)
                .name("brand:update")
                .url("/brand/update/**")
                .build());
        resourceList.add(UmsResource.builder()
                .id(3L)
                .name("brand:delete")
                .url("/brand/delete/**")
                .build());
        resourceList.add(UmsResource.builder()
                .id(4L)
                .name("brand:list")
                .url("/brand/list")
                .build());
        resourceList.add(UmsResource.builder()
                .id(5L)
                .name("brand:listAll")
                .url("/brand/listAll")
                .build());
    }

    @Override
    public AdminUserDetails getAdminByUsername(String username) {
        List<AdminUserDetails> collect = adminUserDetailsList.stream().filter(item -> item.getUsername().equals(username)).toList();
        if (Objects.nonNull(collect)) {
            return collect.get(0);
        }

        return null;
    }

    @Override
    public List<UmsResource> getResourceList() {
        return resourceList;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = getAdminByUsername(username);
            if (userDetails == null) {
                return token;
            }
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
}