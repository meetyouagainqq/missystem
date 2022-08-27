package com.javasm.system.dao.series.impl;

import com.javasm.system.dao.series.SeriesDao;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.Series;
import com.javasm.system.entity.SeriesRemittance;
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
    public List<Series> getSeries(Integer page,Integer pageSize,Series querySeries) {
        Connection conn = DruidUtils.getConnection();
        String sql = " select ps1.* from product_series ps1  ";

        ArrayList<Object> paramList = new ArrayList<>();
//        Boolean isWhere = true;
        if(querySeries.getSname()!=null&& !"".equals(querySeries)){

//            if(isWhere){
//                sql+= " where ";
//                isWhere = false;
//            }else{
//                sql+= " and ";
//            }
            sql +=" where ps1.sname like ? ";
            paramList.add("%"+querySeries.getSname()+"%");
        }

        sql +=" limit "+(page-1)*pageSize+","+pageSize;
        System.out.println(sql);

        List<Series> seriesList = null;
        try {
            seriesList = new QueryRunner().query(conn, sql,
                    new BeanListHandler<>(Series.class, new BasicRowProcessor(new GenerousBeanProcessor())),
                    paramList.toArray());
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
    public Long getAllSeriesNum(Series querySeries) {
        String sql = "SELECT COUNT(1) as num FROM product_series ps1 ";
        Connection connection = DruidUtils.getConnection();

        ArrayList<Object> paramList = new ArrayList<>();
//        Boolean isWhere = true;
        if(querySeries.getSname()!=null&& !"".equals(querySeries)){

//            if(isWhere){
//                sql+= " where ";
//                isWhere = false;
//            }else{
//                sql+= " and ";
//            }
            sql +=" where ps1.sname like ? ";
            paramList.add("%"+querySeries.getSname()+"%");
        }
        Long totalNum = 0L;
        try {
            totalNum = new QueryRunner().query(connection, sql, new ScalarHandler<Long>(),paramList.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return totalNum;
    }

    @Override
    public Integer editSeries(SeriesRemittance editSeries) {
        Connection conn = DruidUtils.getConnection();
        String sql = "update product_series set sname=? ,sename = ?,remittance = ?,bank_name= ? ,bank_swift= ? ,bank_code= ? ," +
                "cnaps_number= ? ,bank_area= ? ,bank_city= ? ,payee_name= ? ,payee_account= ? ," +
                "payee_address= ? ,Information_organization= ? where sid = ? ";
        System.out.println(sql);


        Integer resNum = 0;
        try {
            resNum = new QueryRunner().update(conn, sql,
                    editSeries.getSname(),
                    editSeries.getSename(),
                    editSeries.getRemittance(),
                    editSeries.getBankName(),
                    editSeries.getBankSwift(),
                    editSeries.getBankCode(),
                    editSeries.getCnapsNumber(),
                    editSeries.getBankArea(),
                    editSeries.getBankCity(),
                    editSeries.getPayeeName(),
                    editSeries.getPayeeAccount(),
                    editSeries.getPayeeAddress(),
                    editSeries.getInformationOrganization(),
                    editSeries.getSid());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resNum;
    }

    @Override
    public SeriesRemittance getSeriesById(Integer sid) {
        Connection conn = DruidUtils.getConnection();
        String sql = " select * from product_series ps1 where ps1.sid=? ";
        System.out.println(sql);
        SeriesRemittance currenSeries = null;
        try {
            currenSeries = new QueryRunner().query(conn, sql,
                    new BeanHandler<>(SeriesRemittance.class,new BasicRowProcessor(new GenerousBeanProcessor())),
                    sid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtils.close(conn);
        }

        return currenSeries;
    }

    @Override
    public Integer editForSeries(Series editforSeries) {
        Connection conn = DruidUtils.getConnection();
        String sql = "UPDATE product_series set sname = ?, sename= ?,remittance=? where sid = ? ";
        System.out.println(sql);
        Integer Num = 0;
        try {
            Num = new QueryRunner().update(conn, sql,
                    editforSeries.getSname(),
                    editforSeries.getSename(),
                    editforSeries.getRemittance(),
                    editforSeries.getSid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Num;
    }
}




