package com.javasm.system.dao;

import com.javasm.system.entity.ProductRecommend;

import java.util.List;

/**
 * @author:Dai
 * @className:ProductRecommendDao
 * @description:
 * @date:2022/8/23 11:12
 * @version: 1.0
 * @since: jdk11
 */
public interface ProductRecommendDao {
    List<ProductRecommend> query();



    //添加的服务接口
     Integer addProd(ProductRecommend insertProd);

//分页查询
     List<ProductRecommend>getAllProduct(Integer page,Integer pageSize);

//总记录数
    Long getAllProductNum();
}
