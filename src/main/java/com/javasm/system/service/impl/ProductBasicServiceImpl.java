package com.javasm.system.service.impl;

import com.javasm.system.dao.ProductBasicDao;
import com.javasm.system.dao.impl.ProductBasicDaoImpl;
import com.javasm.system.entity.ProductBasic;
import com.javasm.system.entity.ProductInCrease;
import com.javasm.system.entity.ProductType;
import com.javasm.system.service.ProductBasicService;

import java.util.List;

public class ProductBasicServiceImpl implements ProductBasicService {
    private ProductBasicDao basicDao=new ProductBasicDaoImpl();
    @Override
    public List<ProductBasic> getProductListByQuery(Integer page, Integer pageSize, ProductBasic queryParam) {
        List<ProductBasic> basicList = basicDao.getProductListByQuery(page, pageSize, queryParam);
        return basicList;
    }

    @Override
    public Long getBasicNumByQuery(ProductBasic queryParam) {
        Long num = basicDao.getBasicNumByQuery(queryParam);
        return num;
    }

    @Override
   public List<ProductType> getProductSelect(){
        List<ProductType> productSelect = basicDao.getProductSelect();
        return productSelect;
    }

    @Override
    public Integer addProduct(ProductBasic productBasic) {
        Integer num = basicDao.addProduct(productBasic);
        return num;
    }
    @Override
    public ProductBasic getBasicById(Integer id) {
        ProductBasic basic = basicDao.getBasicById(id);
        return basic;
    }

    @Override
    public Integer editBasicById(ProductBasic basic) {
        Integer num = basicDao.editBasicById(basic);
        return num;
    }

    @Override
    public String getBasicName(Integer id) {
        String basicName = basicDao.getBasicName(id);
        return basicName;
    }

    @Override
    public Integer addWorth(ProductInCrease crease) {
        Integer num = basicDao.addWorth(crease);
        return num;
    }
}
