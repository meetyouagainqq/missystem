package com.javasm.system.service;

import com.javasm.system.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getProductListByQuery(Integer page, Integer pageSize, Review queryParam);
    Long getNumByQuery(Review queryParam);
}
