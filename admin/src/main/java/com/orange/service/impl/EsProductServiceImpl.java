package com.orange.service.impl;

import com.orange.dao.EsProductDao;
import com.orange.nosql.elasticsearch.document.EsProduct;
import com.orange.nosql.elasticsearch.repository.EsProductRepository;
import com.orange.service.EsProductService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EsProductServiceImpl implements EsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class.getSimpleName());

    @Autowired
    EsProductRepository esProductRepository;
    @Autowired
    EsProductDao esProductDao;

    @Override
    public int importAll() {
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = esProductRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            iterator.next();
            result++;
        }

        return result;
    }

    @Override
    public void delete(long id) {
        esProductRepository.deleteById(id);
    }

    @Override
    public EsProduct create(long id) {
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(null);
        if (CollectionUtils.isNotEmpty(esProductList)) {
            return esProductRepository.save(esProductList.get(0));
        }
        return null;
    }

    @Override
    public void delete(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            List<EsProduct> products = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                products.add(esProduct);
            }
            esProductRepository.deleteAll(products);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, int pageNum, int pageSize) {
        PageRequest page = PageRequest.of(pageNum, pageSize);
        return esProductRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, page);
    }
}
