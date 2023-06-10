package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.domain.AdminUserDetails;
import com.example.springsecuritydemo.domain.UmsResource;

import java.util.List;

/**
 * @author Romic
 * @date 2023/6/10
 * @description
 */
public interface UmsAdminService {

    /**
     * 通过username获取用户信息
     *
     * @param username
     * @return
     */
    AdminUserDetails getAdminByUsername(String username);

    /**
     * 获取所以权限列表
     */
    List<UmsResource> getResourceList();

    /**
     * 用户名密码登录
     */
    String login(String username, String password);
}
