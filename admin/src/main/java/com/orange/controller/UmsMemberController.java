package com.orange.controller;

import com.orange.api.CommonResult;
import com.orange.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录注册管理
 */
@Api("用户登录注册管理")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    UmsMemberService umsMemberService;

    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ApiOperation("获取验证码")
    public CommonResult<String> getAuthCode(String telephone) {
        if (StringUtils.isBlank(telephone)) return CommonResult.failed("手机号为空");
        return CommonResult.success(umsMemberService.generatorCode(telephone));
    }

    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ApiOperation("校验验证码")
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        return CommonResult.message(umsMemberService.verifyCode(telephone, authCode), "验证成功", "验证失败");
    }
}
