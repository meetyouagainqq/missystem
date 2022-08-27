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




    //获取普通推荐的产品
    List<ProductRecommend>dataForRel();



    //根据id修改指定字段
    Integer EditProduct( ProductRecommend editProd);

      //模糊查询
    List<ProductRecommend>getProductByPName( Integer page,Integer pageSize,String name);

    //获取添加的下拉菜单
    List<ProductRecommend>getDropDownList();


    //菜单数据
    List<ProductRecommend> getAllProducts();



    //添加的服务接口
     Integer addProd(ProductRecommend insertProd);

//分页查询
     List<ProductRecommend>getPaging(Integer page,Integer pageSize);

//总记录数
    Long getAllProductNum(String name);

    //根据id 查询数据
    ProductRecommend getProductById(Integer pid);
}
