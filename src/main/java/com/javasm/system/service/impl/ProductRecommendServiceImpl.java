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
    public List<ProductRecommend> dataForRel() {
        return new ProductRecommendImpl().dataForRel();
    }

    @Override
    public ProductRecommend getProductById(Integer pid) {
        return new ProductRecommendImpl().getProductById(pid);
    }

    @Override
    public Integer EditProduct(ProductRecommend editProd) {
        return new ProductRecommendImpl().EditProduct(editProd);
    }

    @Override
    public List<ProductRecommend> getAllProducts() {

        return new ProductRecommendImpl().getAllProducts();
    }

    @Override
    public Integer addProd(ProductRecommend insertProd) {
        return new ProductRecommendImpl().addProd(insertProd);
    }

    @Override
    public List<ProductRecommend> getPaging(Integer page, Integer pageSize) {
        return new ProductRecommendImpl().getPaging(page,pageSize);
    }

    @Override
    public Long getAllProductNum(String name) {

        return new ProductRecommendImpl().getAllProductNum(name);
    }

    @Override
//    获取已经审核的产品
//            然后再判断这些产品中的推荐状态值为0的数据拼接到再添加到下拉列表展示
    public List<ProductRecommend> getDropDownList() {
        return new ProductRecommendImpl().getDropDownList();
    }

    @Override
    public List<ProductRecommend> getProductByPName(Integer page,Integer pageSize, String name) {
        return new ProductRecommendImpl().getProductByPName(page,pageSize,name);
    }
}
