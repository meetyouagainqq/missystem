package com.javasm.system.dao.impl;

import com.javasm.system.dao.ReviewDao;
import com.javasm.system.entity.ProductBasic;
import com.javasm.system.entity.Review;
import com.javasm.system.util.DruidUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    @Override
    public List<Review> getProductListByQuery(Integer page, Integer pageSize, Review queryParam) {
        String sql = "select * from product_review pr";
        Connection connection = DruidUtils.getConnection();
        List<Review> basicList = new ArrayList<>();
        List<Object> paramList = new ArrayList<>();
        boolean flag = true;
        if (queryParam.getPname() != null && !"".equals(queryParam.getPname())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pr.pname like ?";
            paramList.add("%" + queryParam.getPname() + "%");
        }
        //审核状态
        if (queryParam.getStatus() != null && !"".equals(queryParam.getStatus())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pr.status = ?";
            paramList.add(queryParam.getStatus());
        }
        sql += " limit " + (page - 1) * pageSize + " , " + pageSize;
        try {
            basicList = new QueryRunner().query(connection, sql, new BeanListHandler<>(Review.class, new BasicRowProcessor(new GenerousBeanProcessor())), paramList.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return basicList;
    }

    @Override
    public Long getNumByQuery(Review queryParam) {
        String sql = "select count(1) as num from product_review pr";
        Connection connection = DruidUtils.getConnection();
        List<Object> paramList = new ArrayList<>();
        boolean flag = true;
        if (queryParam.getPname() != null && !"".equals(queryParam.getPname())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pr.pname like ?";
            paramList.add("%" + queryParam.getPname() + "%");
        }
        //审核状态
        if (queryParam.getStatus() != null && !"".equals(queryParam.getStatus())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pr.status = ?";
            paramList.add(queryParam.getStatus());
        }
        long totalNum = 0;
        try {
            totalNum = new QueryRunner().query(connection, sql, new ScalarHandler<Long>(), paramList.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return totalNum;
    }
}
