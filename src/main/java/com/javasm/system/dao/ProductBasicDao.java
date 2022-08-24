package com.javasm.system.dao;

import com.javasm.system.entity.ProductBasic;
import com.javasm.system.entity.ProductInCrease;
import com.javasm.system.entity.ProductType;

import java.util.List;

public interface ProductBasicDao {
    List<ProductBasic> getProductListByQuery(Integer page, Integer pageSize, ProductBasic queryParam);
    Long getBasicNumByQuery(ProductBasic queryParam);
    List<ProductType> getProductSelect();
    Integer addProduct(ProductBasic productBasic);
    ProductBasic getBasicById(Integer id);
    Integer editBasicById(ProductBasic basic);
    String getBasicName(Integer id);
    Integer addWorth(ProductInCrease crease);
}
