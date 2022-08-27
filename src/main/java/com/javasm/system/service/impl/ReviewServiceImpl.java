package com.javasm.system.service.impl;

import com.javasm.system.dao.ReviewDao;
import com.javasm.system.dao.impl.ReviewDaoImpl;
import com.javasm.system.entity.ProductBasic;
import com.javasm.system.entity.Review;
import com.javasm.system.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private ReviewDao reviewDao = new ReviewDaoImpl();

    @Override
    public List<Review> getProductListByQuery(Integer page, Integer pageSize, Review queryParam) {
        return reviewDao.getProductListByQuery(page, pageSize, queryParam);
    }

    @Override
    public Long getNumByQuery(Review queryParam) {
        return reviewDao.getNumByQuery(queryParam);
    }

    @Override
    public ProductBasic getBasicById(Integer id) {
        return reviewDao.getBasicById(id);
    }

    @Override
    public Integer rollbackStatus(Integer id) {
        return reviewDao.rollbackStatus(id);
    }

    @Override
    public Integer reviewStatus(Integer id) {
        return reviewDao.reviewStatus(id);
    }


}
