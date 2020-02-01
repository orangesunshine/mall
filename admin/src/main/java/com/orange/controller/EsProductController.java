package com.orange.controller;

import com.orange.api.CommonPage;
import com.orange.api.CommonResult;
import com.orange.nosql.elasticsearch.document.EsProduct;
import com.orange.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "EsProductController", description = "根据名字、副标题，查找商品")
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到es")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    public CommonResult<Integer> importAllProduct() {
        return CommonResult.success(esProductService.importAll());
    }

    @ApiOperation(value = "根据商品id删除es记录")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable("id") long id) {
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "删除es所有商品记录")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据数据库id的商品，新增es商品记录")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public CommonResult<EsProduct> create(@PathVariable long id) {
        return CommonResult.success(esProductService.create(id));
    }

    @ApiOperation(value = "根据数据库id的商品，新增es商品记录")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") int pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") int pageSize) {
        return CommonResult.success(CommonPage.restPage(esProductService.search(keyword, pageNum, pageSize)));
    }
}
