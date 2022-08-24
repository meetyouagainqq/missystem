package com.javasm.system.test;

import com.javasm.system.dao.ProductRecommendDao;
import com.javasm.system.dao.impl.ProductRecommendImpl;
import com.javasm.system.entity.ProductRecommend;
import com.javasm.system.service.ProductRecommendService;
import com.javasm.system.service.impl.ProductRecommendServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @author:Dai
 * @className:DaiTest
 * @description:
 * @date:2022/8/23 15:02
 * @version: 1.0
 * @since: jdk11
 */
public class DaiTest {
    @Test
    public  void test3(){
        //总计录数的测试
        ProductRecommendDao pro=new ProductRecommendImpl();
        Long allProductNum = pro.getAllProductNum();
        System.out.println(allProductNum);
    }

    @Test
    public  void test2(){
        //分页查询
        ProductRecommendDao pro=new ProductRecommendImpl();
        List<ProductRecommend> allProduct = pro.getAllProduct(2, 3);
        System.out.println(allProduct);


    }
    @Test
    public  void test1(){
        //添加测试
ProductRecommendDao prodRec=new ProductRecommendImpl();
       ProductRecommend pro=new ProductRecommend(0,0,0,0,"00000011");
        Integer integer = prodRec.addProd(pro);
        System.out.println(integer);


    }
}
