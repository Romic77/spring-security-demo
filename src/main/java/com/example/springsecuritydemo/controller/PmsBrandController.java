package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;


/**
 * 品牌管理Controller
 * Created by macro on 2019/4/19.
 */
@Controller

@RequestMapping("/brand")
public class PmsBrandController {


    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);


    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<String>> getBrandList() {
        return CommonResult.success(Arrays.asList("腾讯", "华为", "知乎"));
    }

}