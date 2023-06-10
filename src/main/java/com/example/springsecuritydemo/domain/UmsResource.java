package com.example.springsecuritydemo.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Romic
 * @date 2023/6/10
 * @description
 */
@Data
@Builder
public class UmsResource {
    private Long id;

    private Date createTime;

    private String name;

    private String url;

    private String description;

    private Long categoryId;


}
