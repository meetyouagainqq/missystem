package com.javasm.system.dao;



import com.javasm.system.entity.ProductBasic;
import com.javasm.system.entity.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getProductListByQuery(Integer page, Integer pageSize, Review queryParam);
    Long getNumByQuery(Review queryParam);
    ProductBasic getBasicById(Integer id);
    //驳回基金审核
    Integer rollbackStatus(Integer id);
    //通过基金审核
    Integer reviewStatus(Integer id);
}
