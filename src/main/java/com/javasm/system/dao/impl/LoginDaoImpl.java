package com.javasm.system.dao.impl;

import com.javasm.system.dao.LoginDao;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.User;
import com.javasm.system.util.DruidUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDaoImpl implements LoginDao {
    @Override
    public User loginUser(User user) {
        String sql = "SELECT * FROM pn_admin_user pau WHERE pau.username= ? AND pau.password = ? ";
        Connection connection = DruidUtils.getConnection();
        User loginUser = null;
        try {
            loginUser = new QueryRunner().query(connection, sql, new BeanHandler<>(User.class, new BasicRowProcessor(new GenerousBeanProcessor()))
                    , user.getUsername(), user.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return loginUser;
    }

    @Override
    public String getUserMenuId(Integer userId) {
        String sql = "SELECT pau.menu_id FROM pn_admin_user pau WHERE pau.uid = ?";
        Connection connection = DruidUtils.getConnection();
        String menuIds = "";
        try {
            menuIds = new QueryRunner().query(connection, sql, new ScalarHandler<String>(), userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return menuIds;
    }

    @Override
    public List<Menu> getMenuListByLevel(String menuIds, Integer level) {
        Connection connection = DruidUtils.getConnection();
        String sql = "SELECT * FROM pn_admin_menu pam WHERE ";
        if (level == 1) {
            //父级菜单
            sql += "pam.pid = 0 ";
        } else if (level == 2) {
            sql += "pam.pid !=0 ";
        }
        sql += "AND pam.mid IN (" + menuIds + ")";
        List<Menu> menuList = new ArrayList<>();
        try {
            menuList = new QueryRunner().query(connection, sql,
                    new BeanListHandler<>(Menu.class, new BasicRowProcessor(new GenerousBeanProcessor())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return menuList;
    }
    @Override
    public List<String> getMenuUrlById(String menuIds) {
        Connection connection = DruidUtils.getConnection();
        String sql = "SELECT url FROM pn_admin_menu pam WHERE pam.pid != 0";
        sql += " AND pam.mid IN (" + menuIds + ")";
        List<String> menuUrl = null;
        try {
            menuUrl = new QueryRunner().query(connection, sql,
                    new ColumnListHandler<String>("url"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return menuUrl;
    }

}
