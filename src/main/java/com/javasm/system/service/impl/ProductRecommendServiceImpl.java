package com.javasm.system.service.impl;

import com.javasm.system.dao.ProductRecommendDao;
import com.javasm.system.dao.impl.ProductRecommendImpl;
import com.javasm.system.entity.ProductRecommend;
import com.javasm.system.service.ProductRecommendService;

import java.util.List;

/**
 * @author:Dai
 * @className:ProductRecommendImpl
 * @description:
 * @date:2022/8/23 11:11
 * @version: 1.0
 * @since: jdk11
 */
public class ProductRecommendServiceImpl implements ProductRecommendService {

    @Override
    public List<ProductRecommend> query() {

        return new ProductRecommendImpl().query();
    }

    @Override
    public Integer addProd(ProductRecommend insertProd) {
        return new ProductRecommendImpl().addProd(insertProd);
    }

    @Override
    public List<ProductRecommend> getAllProduct(Integer page, Integer pageSize) {
        return new ProductRecommendImpl().getAllProduct(page,pageSize);
    }

    @Override
    public Long getAllProductNum() {

        return new ProductRecommendImpl().getAllProductNum();
    }
}
