package com.javasm.system.dao.impl;

import com.javasm.system.dao.ProductRecommendDao;
import com.javasm.system.entity.ProductRecommend;
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
 * @author:Dai
 * @className:ProductRecommendImpl
 * @description:
 * @date:2022/8/23 11:12
 * @version: 1.0
 * @since: jdk11
 */

public class ProductRecommendImpl implements ProductRecommendDao {
    //SELECT pr.*,pb.pname FROM product_recommend AS pr  LEFT JOIN   product_basic AS pb ON  pr.pb_id = pb.id where pb.status=1 AND pr.finer=0




    @Override
    public List<ProductRecommend> dataForRel() {
        //获取普通推荐的产品
        Connection connection=DruidUtils.getConnection();
        String sql="SELECT pr.*,pb.pname FROM product_recommend AS pr  LEFT JOIN   product_basic AS pb ON  pr.pb_id = pb.id where pb.status=1 AND pr.finer=0";

        List <ProductRecommend> relList=new ArrayList<>();

        try {
            relList= new QueryRunner().query(connection,sql,
                    new BeanListHandler<>(ProductRecommend.class,
                            new BasicRowProcessor(new GenerousBeanProcessor())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return relList;
    }
    @Override
    public ProductRecommend getProductById(Integer pid) {
        //根据id 查询数据
        Connection connection= DruidUtils.getConnection();
        String sql="SELECT pr.*, pb.pname,pb.id FROM product_recommend AS pr LEFT JOIN product_basic AS pb ON pr.pb_id = pb.id WHERE pr.pb_id =?";
        System.out.println(sql);

        ProductRecommend currentProd=null;

        try {
            currentProd = new QueryRunner().query(connection, sql, new BeanHandler<>(
                            ProductRecommend.class,
                    new BasicRowProcessor(new GenerousBeanProcessor())),pid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtils.close(connection);
        }


        return currentProd;
    }



    @Override
    public Integer EditProduct(ProductRecommend editProd) {
        //根据id修改指定字段
        Connection connection= DruidUtils.getConnection();
        String sql="UPDATE product_recommend AS pr set finer=?,visible=?,issue=?,subscribe=?,`explain`=? WHERE pr.pb_id=?";
   Integer num=0;


        try {
            num= new QueryRunner().update(connection,sql,

                    editProd.getFiner(),
                    editProd.getVisible(),
                    editProd.getIssue(),
                    editProd.getSubscribe(),
                    editProd.getExplain(),
                    editProd.getId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtils.close(connection);
        }
   return num;
}
//
    @Override
    public List<ProductRecommend> getProductByPName( Integer page,Integer pageSize,String name) {
        //模糊查询

        Connection connection= DruidUtils.getConnection();
        String sql="SELECT pr.*,pb.pname FROM product_recommend AS pr  LEFT JOIN   product_basic AS pb\n" +
                "on pr.pb_id = pb.id where pb.status=1";

        List<ProductRecommend> prodList = new ArrayList<>();
        List<Object> paramList = new ArrayList<>();

        if (name != null && !"".equals(name)) {

            sql += "  AND  pb.pname  like ? ";
            paramList.add("%" + name + "%");
        }

     sql += " limit " + (page - 1) * pageSize + "," + pageSize;
        System.out.println(sql);
        try {
            prodList = new QueryRunner().query(connection, sql, new BeanListHandler<>(
                            ProductRecommend.class, new BasicRowProcessor(new GenerousBeanProcessor())),
                    paramList.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return prodList;


    }



    @Override
    public List<ProductRecommend> getDropDownList(){
    //获得添加>推荐产品下拉列表
        Connection connection= DruidUtils.getConnection();
        String sql="SELECT pr.*,pb.pname,pb.id FROM product_recommend AS pr  LEFT JOIN   product_basic AS pb on pr.pb_id = pb.id where pb.status=1 AND pr.is_finer=0";
       //获取已经审核的产品
        //然后再判断这些产品中的推荐状态值为0的数据拼接到再添加到下拉列表中
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
    public List<ProductRecommend> getAllProducts() {
//获取菜单数据
        Connection connection= DruidUtils.getConnection();
        String sql="SELECT pr.*,pb.pname FROM product_recommend AS pr  LEFT JOIN   product_basic AS pb\n" +
                "on pr.pb_id = pb.id where pb.status=1";
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
    public Long getAllProductNum(String name) {
        //查询总记录数
        Connection connection=DruidUtils.getConnection();
        String sql=" SELECT count(1)FROM product_recommend AS pr , product_basic AS pb WHERE pr.pb_id = pb.id AND pb.status=1 AND pr.is_status=1 ";


        List<ProductRecommend> prodList = new ArrayList<>();
        List<Object> paramList = new ArrayList<>();

        if (name != null && !"".equals(name)) {

            sql += "  AND  pb.pname  like ? ";
            paramList.add("%" + name + "%");
        }

        System.out.println(sql);
        Long tatalNum=0L;
        try {
            tatalNum= new QueryRunner().query(connection,sql,
                    new ScalarHandler<Long>(),paramList.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tatalNum;
    }



    @Override//错误的 需要更改
    public Integer addProd(ProductRecommend insertProd) {
        //添加
        Connection connection= DruidUtils.getConnection();
        String sql="INSERT INTO product_recommend (pb_id,finer,visible,issue,subscribe,`explain`) VALUES(?,?,?,?,?,?)";
        System.out.println(sql);

        Integer resNum=0;
        try {
           resNum= new QueryRunner().update(connection,sql,
                   insertProd.getId(),
                    insertProd.getFiner(),
                    insertProd.getVisible(),
                    insertProd.getIssue(),
                    insertProd.getSubscribe(),
                    insertProd.getExplain()
                   );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtils.close(connection);
        }
        return resNum;




    }

    @Override
    public List<ProductRecommend> getPaging(Integer page,Integer pageSize) {
        //分页
        Connection connection=DruidUtils.getConnection();
        String sql="SELECT pb.pname,pb.id ,pr.finer,pr.issue,pr.subscribe,pr.sort FROM product_basic AS pb , product_recommend AS pr where  pb.id = pr.pb_id AND pb.`status`=1" ;

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
