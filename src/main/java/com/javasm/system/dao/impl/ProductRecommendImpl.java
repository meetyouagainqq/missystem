package com.javasm.system.dao.impl;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.javasm.system.dao.ProductRecommendDao;
import com.javasm.system.entity.ProductRecommend;
import com.javasm.system.test.JDBCTest;
import com.javasm.system.util.DruidUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.servlet.annotation.WebFilter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:Dai
 * @className:ProductRecommendImpl
 * @description:
 * @date:2022/8/23 11:12
 * @version: 1.0
 * @since: jdk11
 */

public class ProductRecommendImpl implements ProductRecommendDao {
    @Override
    public List<ProductRecommend> query() {
        Connection connection= DruidUtils.getConnection();
        String sql="SELECT * FROM `product_recommend`";
        System.out.println(sql);
        List<ProductRecommend>ProRec=new ArrayList<>();
        try {
            ProRec=new QueryRunner().query(connection,sql,new BeanListHandler<ProductRecommend>(ProductRecommend.class,new BasicRowProcessor(new GenerousBeanProcessor())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtils.close(connection);
        }
        return ProRec;
    }

    @Override
    public Long getAllProductNum() {
        //查询总记录数
        Connection connection=DruidUtils.getConnection();
        String sql=" select count(1) as totalnum from product_basic  AS pb WHERE  pb.status=1 ";

        Long tatalNum=0L;

        try {
            tatalNum= new QueryRunner().query(connection,sql,new ScalarHandler<Long>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tatalNum;
    }

    @Override
    public Integer addProd(ProductRecommend insertProd) {
        Connection connection= DruidUtils.getConnection();
        String sql="INSERT INTO product_recommend (finer,visible,issue,subscribe,`explain`) VALUES(?,?,?,?,?)";
        System.out.println(sql);

        Integer resNum=0;
        try {
           resNum= new QueryRunner().update(connection,sql,
                    insertProd.getFiner(),
                    insertProd.getVisible(),
                    insertProd.getIssue(),
                    insertProd.getSubscribe(),
                    insertProd.getExplain());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtils.close(connection);
        }
        return resNum;




    }

    @Override
    public List<ProductRecommend> getAllProduct(Integer page,Integer pageSize) {
        Connection connection=DruidUtils.getConnection();
        String sql="SELECT pb.pname ,pr.finer,pr.issue,pr.subscribe,pr.sort FROM product_basic AS pb RIGHT JOIN product_recommend AS pr ON pb.`status`=pr.is_status AND pb.`status`=1" ;

        sql+= " LIMIT "+(page-1)*pageSize+","+pageSize;
        System.out.println(sql);
        List<ProductRecommend>prod=null;
        try {
            prod=new  QueryRunner().query(connection,sql,
                    new BeanListHandler<ProductRecommend>(
                            ProductRecommend.class,new BasicRowProcessor(
                                    new GenerousBeanProcessor())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtils.close(connection);
        }


        return prod;
    }
}
