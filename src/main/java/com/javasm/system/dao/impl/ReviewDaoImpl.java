package com.javasm.system.dao.impl;

import com.javasm.system.dao.ReviewDao;
import com.javasm.system.entity.ProductBasic;
import com.javasm.system.entity.Review;
import com.javasm.system.util.DruidUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    @Override
    public List<Review> getProductListByQuery(Integer page, Integer pageSize, Review queryParam) {
        String sql = "SELECT pr.*,pb.status FROM  product_basic pb  JOIN product_review pr on pr.basic_id=pb.id ";
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
            sql += " pb.status = ?";
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
        String sql = "  SELECT count(1) as num FROM  product_basic pb  JOIN product_review pr on pr.basic_id=pb.id";
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
            sql += " pb.status = ?";
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

    @Override
    public ProductBasic getBasicById(Integer id) {
        String sql = "SELECT pb.* FROM  product_basic pb JOIN product_review pr on pr.basic_id=pb.id WHERE pr.id=?";
        Connection connection = DruidUtils.getConnection();
        ProductBasic basic = null;
        try {
            basic = new QueryRunner().query(connection, sql, new BeanHandler<>(ProductBasic.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return basic;
    }

    @Override
    public Integer rollbackStatus(Integer id) {
        String sql = "UPDATE product_basic pb,product_review pr SET pb.status = 0 WHERE pb.id = pr.basic_id AND pr.id = ?";
        Connection connection = DruidUtils.getConnection();
        Integer num = 0;
        try {
            num = new QueryRunner().update(connection, sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return num;
    }

    @Override
    public Integer reviewStatus(Integer id) {
        String sql = "UPDATE product_basic pb,product_review pr SET pb.status = 1 WHERE pb.id = pr.basic_id AND pr.id = ?";
        Connection connection = DruidUtils.getConnection();
        Integer num = 0;
        try {
            num = new QueryRunner().update(connection, sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return num;
    }


}
