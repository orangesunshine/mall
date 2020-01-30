package com.orange.controller;

import com.orange.api.CommonPage;
import com.orange.api.CommonResult;
import com.orange.model.User;
import com.orange.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户管理
 */
@Api(tags = "UserController", value = "后台用户管理")
@RestController
@RequestMapping("/user")
public class UmsAdminController {
    @Autowired
    UmsAdminService umsAdminService;

    @ApiOperation("用户列表（分页）")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public CommonResult<CommonPage<User>> listPageUser(int pageNum, int pageSize) {
        List<User> page = umsAdminService.page(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiOperation("用户列表（全部）")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public CommonResult<List<User>> listAllUser() {
        return CommonResult.success(umsAdminService.listAll());
    }

    @ApiOperation("新增用户")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    CommonResult insertUser(@RequestBody User user) {
        return CommonResult.message(umsAdminService.insert(user) > 0, "新增成功", "新增失败");
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    CommonResult deleteUser(@PathVariable("id") long id) {
        return CommonResult.message(umsAdminService.delete(id) > 0, "删除成功", "删除失败");
    }

    @ApiOperation("更新用户")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    CommonResult updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return CommonResult.message(umsAdminService.update(id, user) > 0, "更新成功", "更新失败");
    }

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    CommonResult<User> listUser(@PathVariable("id") long id) {
        return CommonResult.success(umsAdminService.list(id));
    }
}
