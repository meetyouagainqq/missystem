package com.javasm.system.dao.impl;


import com.javasm.system.dao.ProductBasicDao;
import com.javasm.system.entity.ProductBasic;
import com.javasm.system.entity.ProductInCrease;
import com.javasm.system.entity.ProductType;
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

public class ProductBasicDaoImpl implements ProductBasicDao {

    @Override
    public List<ProductBasic> getProductListByQuery(Integer page, Integer pageSize, ProductBasic queryParam) {
        String sql = "select * from product_basic pb";
        Connection connection = DruidUtils.getConnection();
        List<ProductBasic> basicList = new ArrayList<>();
        List<Object> paramList = new ArrayList<>();
        boolean flag = true;
        if (queryParam.getPname() != null && !"".equals(queryParam.getPname())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pb.pname like ?";
            paramList.add("%" + queryParam.getPname() + "%");
        }
        //产品类型查询
        if (queryParam.getClassification() != null && !"".equals(queryParam.getClassification())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pb.classification = ?";
            paramList.add(queryParam.getClassification());
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
            basicList = new QueryRunner().query(connection, sql, new BeanListHandler<>(ProductBasic.class, new BasicRowProcessor(new GenerousBeanProcessor())), paramList.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return basicList;
    }

    @Override
    public Long getBasicNumByQuery(ProductBasic queryParam) {
        String sql = "select count(1) as num from product_basic pb";
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
            sql += " pb.pname like ?";
            paramList.add("%" + queryParam.getPname() + "%");
        }
        //产品类型查询
        if (queryParam.getClassification() != null && !"".equals(queryParam.getClassification())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pb.classification = ?";
            paramList.add(queryParam.getClassification());
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
    public List<ProductType> getProductSelect() {
        String sql = "SELECT pt.classification,pt.name FROM product_type pt";
        Connection connection = DruidUtils.getConnection();
        List<ProductType> basicList = new ArrayList<>();
        try {
            basicList = new QueryRunner().query(connection, sql, new BeanListHandler<>(ProductType.class, new BasicRowProcessor(new GenerousBeanProcessor())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return basicList;
    }

    @Override
    public Integer addProduct(ProductBasic basic) {
        String sql = "INSERT INTO product_basic (pname,series,classification,mechanism_name,open_time,currency,status,yield,review_person,cycle,management_rate,subscription_rate,amount,method,redemption_period,starting_amount,redemption_fee,lock_period) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = DruidUtils.getConnection();
        Integer addNum = 0;
        try {
            addNum = new QueryRunner().update(connection, sql,
                    basic.getPname(), basic.getSeries(), basic.getClassification(), basic.getMechanismName(),
                    basic.getOpenTime(), basic.getCurrency(), basic.getStatus(), basic.getYield(),
                    basic.getReviewPerson(), basic.getCycle(), basic.getManagementRate(),
                    basic.getSubscriptionRate(), basic.getAmount(), basic.getMethod(),
                    basic.getRedemptionPeriod(), basic.getStartingAmount(), basic.getRedemptionFee(),
                    basic.getLockPeriod()
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return addNum;
    }

    @Override
    public ProductBasic getBasicById(Integer id) {
        String sql = "SELECT * FROM product_basic pb WHERE pb.id = ?";
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
    public Integer editBasicById(ProductBasic basic) {
        String sql = "UPDATE product_basic pb SET pname = ?,series=?,classification=?,mechanism_name=?,open_time=?,currency=?,status=?,yield=?,review_person=?,cycle=?,management_rate=?,subscription_rate=?,amount=?,method=?,redemption_period=?,starting_amount=?,redemption_fee=?,lock_period=? WHERE pb.id = ?";
        Connection connection = DruidUtils.getConnection();
        Integer num = 0;
        try {
            num = new QueryRunner().update(connection, sql, basic.getPname(),basic.getSeries(),basic.getClassification(),basic.getMechanismName(),
                    basic.getOpenTime(),basic.getCurrency(),basic.getStatus(),basic.getYield(),
                    basic.getReviewPerson(),basic.getCycle(),basic.getManagementRate(),basic.getSubscriptionRate(),basic.getAmount(),
                    basic.getMethod(),basic.getRedemptionPeriod(),basic.getStartingAmount(),basic.getRedemptionFee(),
                    basic.getLockPeriod(),basic.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return num;
    }

    @Override
    public String getBasicName(Integer id) {
        String sql = "select pb.pname from product_basic pb where pb.id = ?";
        Connection connection = DruidUtils.getConnection();
        String pname="";
        try {
            pname = new QueryRunner().query(connection, sql,new ScalarHandler<String>(),id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return pname;
    }

    @Override
    public Integer addWorth(ProductInCrease crease) {
        String sql = "INSERT INTO product_increase (basic_id,net_worth,growth_rate,base_time) VALUES (?,?,?,?)";
        Connection connection = DruidUtils.getConnection();
        Integer addNum = 0;
        try {
            addNum = new QueryRunner().update(connection, sql,crease.getBasicId(),crease.getNetWorth(),crease.getGrowthRate(),crease.getBaseTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return addNum;
    }


}
