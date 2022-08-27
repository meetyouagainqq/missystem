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
    //获取普通推荐的产品
    List<ProductRecommend>dataForRel();


    //根据id 查询数据
    ProductRecommend getProductById(Integer pid);

    //根据id修改指定字段
    Integer EditProduct( ProductRecommend editProd);

    public List<ProductRecommend> getAllProducts();


    Integer addProd(ProductRecommend insertProd);

    List<ProductRecommend>getPaging(Integer page,Integer pageSize);

    Long getAllProductNum(String name);

    //获取添加的下拉菜单
    List<ProductRecommend>getDropDownList();

    //模糊查询
    List<ProductRecommend>getProductByPName(Integer page,Integer pageSize, String name);
}
