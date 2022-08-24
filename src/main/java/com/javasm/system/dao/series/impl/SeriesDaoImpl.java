package com.javasm.system.dao.series.impl;

import com.javasm.system.dao.series.SeriesDao;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.Series;
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

/**
 * @author: LinHai
 * @className: SeriesDaoImpl
 * @Description:
 * @date: 2022/8/23 14:23
 * @version: 0.1
 * @since: jdk11
 */
public class SeriesDaoImpl implements SeriesDao {
    @Override
    public List<Series> getSeries(Integer page,Integer pageSize) {
        Connection conn = DruidUtils.getConnection();
        String sql = " select ps1.* from product_series ps1 where ps1.sid ";
        sql +=" limit "+(page-1)*pageSize+","+pageSize;

        List<Series> seriesList = null;
        try {
            seriesList = new QueryRunner().query(conn, sql,
                    new BeanListHandler<>(Series.class, new BasicRowProcessor(new GenerousBeanProcessor())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtils.close(conn);
        }
        return seriesList;
    }

    @Override
    public Integer addSeries(Series series) {


        Connection conn = DruidUtils.getConnection();
        String sql = "INSERT INTO product_series (sid,sname,sename,remittance) VALUES (?,?,?,?)";
        System.out.println(sql);
        Integer resNum = 0;
        try {
            resNum = new QueryRunner().update(conn, sql,
                    series.getSid(),
                    series.getSname(),
                    series.getSename(),
                    series.getRemittance());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resNum;
        }

    @Override
    public Long getAllSeriesNum() {
        String sql = "SELECT COUNT(1) as num FROM product_series pam";
        Connection connection = DruidUtils.getConnection();

        Long totalNum = 0L;
        try {
            totalNum = new QueryRunner().query(connection, sql, new ScalarHandler<Long>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return totalNum;
    }
}



