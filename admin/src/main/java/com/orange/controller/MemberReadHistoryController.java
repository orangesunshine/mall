package com.orange.controller;

import com.orange.api.CommonResult;
import com.orange.nosql.mongodb.document.MemberReadHistory;
import com.orange.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "MemberReadHistoryController", description = "用户商品浏览记录")
@RestController
@RequestMapping("/history")
public class MemberReadHistoryController {
    @Autowired
    MemberReadHistoryService service;

    @ApiOperation("新增商品浏览记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MemberReadHistory history) {
        return CommonResult.message(service.create(history) > 0, "商品浏览记录新增成功", "商品浏览记录新增失败");
    }

    @ApiOperation("批量删除商品浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam List<String> ids) {
        return CommonResult.message(service.delete(ids) > 0, "商品浏览记录删除成功", "商品浏览记录删除失败");
    }

    @ApiOperation("展示浏览记录")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<List<MemberReadHistory>> list(Long memberId) {
        return CommonResult.success(service.list(memberId));
    }
}
