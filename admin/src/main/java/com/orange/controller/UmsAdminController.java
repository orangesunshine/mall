package com.orange.controller;

import com.google.common.base.Throwables;
import com.orange.api.CommonResult;
import com.orange.model.UmsAdmin;
import com.orange.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台用户管理
 */
@Api(tags = "UserController", value = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    UmsAdminService umsAdminService;

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult<String> login(String username, String password) {
        try {
            return CommonResult.success(umsAdminService.login(username, password));
        } catch (Exception e) {
            return CommonResult.validateFailed(Throwables.getStackTraceAsString(e));
        }

    }


    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin admin) {
        try {
            return CommonResult.success(umsAdminService.register(admin));
        } catch (Exception e) {
            return CommonResult.failed(Throwables.getStackTraceAsString(e));
        }
    }

}
