package com.javasm.system.service;

import com.javasm.system.entity.ProductRecommend;

import java.util.List;

/**
 * @author:Dai
 * @className:ProuctRecommendService
 * @description:
 * @date:2022/8/23 11:10
 * @version: 1.0
 * @since: jdk11
 */
public interface ProductRecommendService {

    public List<ProductRecommend> query();


    Integer addProd(ProductRecommend insertProd);

    List<ProductRecommend>getAllProduct(Integer page,Integer pageSize);

    Long getAllProductNum();
}
