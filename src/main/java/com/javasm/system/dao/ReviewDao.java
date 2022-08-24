package com.javasm.system.dao;



import com.javasm.system.entity.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getProductListByQuery(Integer page, Integer pageSize, Review queryParam);
    Long getNumByQuery(Review queryParam);
}
