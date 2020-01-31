package com.orange.controller;

import cn.hutool.json.JSONUtil;
import com.orange.api.CommonResult;
import com.orange.model.UmsAdmin;
import com.orange.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 后台用户管理
 */
@Api(tags = "UserController", value = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    UmsAdminService umsAdminService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult<String> login(String username, String password) {
        try {
            String token = umsAdminService.login(username, password);
            HashMap<Object, Object> params = new HashMap<>();
            params.put("token", token);
            params.put("tokenHead", tokenHead);

            return CommonResult.success(JSONUtil.toJsonPrettyStr(params));
        } catch (Exception e) {
            return CommonResult.validateFailed(e.getClass().getSimpleName() + ": " + e.getMessage());
        }

    }


    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin admin) {
        try {
            return CommonResult.success(umsAdminService.register(admin));
        } catch (Exception e) {
            return CommonResult.failed(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

}
