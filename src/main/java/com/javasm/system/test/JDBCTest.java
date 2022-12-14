package com.javasm.system.test;

import com.google.gson.Gson;
import com.javasm.system.dao.LoginDao;
import com.javasm.system.dao.MenuDao;
import com.javasm.system.dao.ProductRecommendDao;
import com.javasm.system.dao.impl.LoginDaoImpl;
import com.javasm.system.dao.impl.MenuDaoImpl;
import com.javasm.system.entity.*;
import com.javasm.system.dao.impl.ProductRecommendImpl;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.ProductRecommend;
import com.javasm.system.entity.User;
import com.javasm.system.service.LoginService;
import com.javasm.system.service.MenuService;
import com.javasm.system.service.ProductBasicService;
import com.javasm.system.service.ProductRecommendService;
import com.javasm.system.service.UserService;
import com.javasm.system.service.impl.LoginServiceImpl;
import com.javasm.system.service.impl.MenuServiceImpl;
import com.javasm.system.service.impl.ProductBasicServiceImpl;
import com.javasm.system.service.impl.ProductRecommendServiceImpl;
import com.javasm.system.service.impl.UserServiceImpl;
import com.javasm.system.util.DruidUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCTest {
    @Test
    public void test() {
        LoginService loginService = new LoginServiceImpl();
        User user = new User();
        user.setPassword("abc123");
        user.setUsername("测试1");
        User user1 = loginService.loginUser(user);
        System.out.println(user1);
    }


    @Test
    public void test2() {
        LoginDao loginDao = new LoginDaoImpl();
        String menuIds = "2,4,5";
        List<Menu> menuListByLevel = loginDao.getMenuListByLevel(menuIds, 1);
    }


    @Test
    public void test3() {
        LoginService loginService = new LoginServiceImpl();
        List<Menu> menuList = loginService.getMenusById(9);
        Gson gson = new Gson();
        System.out.println(gson.toJson(menuList));
    }

    @Test
    public void test4() {
        MenuService menuService = new MenuServiceImpl();
        Menu menu = new Menu("", 0);
        Long num = menuService.getMenuNumByQuery(menu);
        System.out.println(num);
    }

    @Test
    public void test5() {
        MenuService menuService = new MenuServiceImpl();
        List<Menu> menuList = menuService.getMenuForSelect();
        System.out.println(menuList);
    }

    @Test
    public void test6() {
        MenuService menuService = new MenuServiceImpl();
        Menu menu = new Menu();
        menu.setMid(200);
        menu.setMenuname("123");
        menu.setGlyphicon("img");
        menu.setPid(0);
        menu.setUrl("123");
        Integer num = menuService.editMenuById(menu);
        System.out.println(num);
    }

    @Test
    public void test7() {
        MenuService menuService = new MenuServiceImpl();
        Menu menu = new Menu();
        menu.setMid(190);
        menu.setVersionid(1);
        Menu menu1 = menuService.regMenuByVersionId(menu);
        System.out.println(menu1);
    }

    @Test
    public void test8() {
        LoginService loginService = new LoginServiceImpl();
        List<String> menuUrlById = loginService.getMenuUrlById(9);
        System.out.println(menuUrlById);

    }

    @Test
    public void test9() {
        UserService userService = new UserServiceImpl();
        String authUserStr=null;
        System.out.println(userService.editUserAuthId(authUserStr, 1));
    }


    @Test
    public void test10() {
        ProductBasicService basicService=new ProductBasicServiceImpl();
        Gson gson = new Gson();
//        ProductBasic basic=new ProductBasic();
//        basic.setPname("哈");
//        Long num = basicService.getBasicNumByQuery(basic);
        List<ProductType> productSelect = basicService.getProductSelect();
        System.out.println(gson.toJson(productSelect));
    }



}
