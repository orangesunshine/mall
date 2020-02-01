package com.orange.service;

import com.orange.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsProductService {
    int importAll();

    void delete(long id);

    EsProduct create(long id);

    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsProduct> search(String keyword, int pageNum, int pageSize);
}
