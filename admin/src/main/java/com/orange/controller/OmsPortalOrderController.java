package com.orange.controller;

import com.orange.api.CommonResult;
import com.orange.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "OmsPortalOrderController", description = "订单管理")
@RestController
@RequestMapping("/order")
public class OmsPortalOrderController {
    @Autowired
    OmsPortalOrderService omsPortalOrderService;

    @ApiOperation("下单")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public CommonResult order(@RequestParam int orderId) {
        return CommonResult.message(omsPortalOrderService.insertOrder(orderId) > 0, "下单成功", "下单失败");
    }
}
