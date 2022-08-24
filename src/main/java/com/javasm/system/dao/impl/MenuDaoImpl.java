package com.javasm.system.dao.impl;

import com.javasm.system.dao.MenuDao;
import com.javasm.system.entity.Menu;
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

public class MenuDaoImpl implements MenuDao {
    @Override
    public List<Menu> getAllMenus(Integer page, Integer pageSize, Menu queryMenu) {
        String sql = "SELECT pam1.*,IFNULL(pam2.menuname,'无') AS pname FROM pn_admin_menu pam1 LEFT JOIN pn_admin_menu pam2 ON pam1.pid=pam2.mid";
        Connection connection = DruidUtils.getConnection();
        List<Menu> menuList = new ArrayList<>();
        List<Object> paramList = new ArrayList<>();
        boolean flag = true;
        if (queryMenu.getMenuname() != null && !"".equals(queryMenu.getMenuname())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pam1.menuname like ? ";
            paramList.add("%" + queryMenu.getMenuname() + "%");
        }
        if (queryMenu.getPid() != null && !"".equals(queryMenu.getPid())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pam1.pid = ? ";
            paramList.add(queryMenu.getPid());
        }
        sql += " limit " + (page - 1) * pageSize + "," + pageSize;
        try {
            menuList = new QueryRunner().query(connection, sql, new BeanListHandler<>(Menu.class, new BasicRowProcessor(new GenerousBeanProcessor())), paramList.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return menuList;
    }

    @Override
    public Long getMenuNumByQuery(Menu queryMenu) {
        String sql = "SELECT COUNT(1) as num FROM pn_admin_menu pam";
        Connection connection = DruidUtils.getConnection();
        List<Object> paramList = new ArrayList<>();
        boolean flag = true;
        if (queryMenu.getMenuname() != null && !"".equals(queryMenu.getMenuname())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pam.menuname like ? ";
            paramList.add("%" + queryMenu.getMenuname() + "%");
        }
        if (queryMenu.getPid() != null && !"".equals(queryMenu.getPid())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pam.pid = ? ";
            paramList.add(queryMenu.getPid());
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
    public List<Menu> getMenuForSelect() {
        String sql = "SELECT * FROM pn_admin_menu pam WHERE pam.pid = 0";
        Connection connection = DruidUtils.getConnection();
        List<Menu> menuList = new ArrayList<>();
        try {
            menuList = new QueryRunner().query(connection, sql, new BeanListHandler<>(Menu.class, new BasicRowProcessor(new GenerousBeanProcessor())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return menuList;
    }

    @Override
    public Integer addMenu(Menu menu) {
        String sql = "INSERT INTO pn_admin_menu (mid,menuname,pid,url,glyphicon) VALUES (?,?,?,?,?)";
        Connection connection = DruidUtils.getConnection();
        Integer addNum = 0;
        try {
            addNum = new QueryRunner().update(connection, sql, menu.getMid(), menu.getMenuname(), menu.getPid(), menu.getUrl(), menu.getGlyphicon());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return addNum;
    }

    @Override
    public Menu getMenuById(Integer mid) {
        String sql = "SELECT * FROM pn_admin_menu pam WHERE pam.mid = ?";
        Connection connection = DruidUtils.getConnection();
        Menu menu = null;
        try {
            menu = new QueryRunner().query(connection, sql, new BeanHandler<>(Menu.class, new BasicRowProcessor(new GenerousBeanProcessor())), mid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return menu;
    }

    @Override
    public Integer deleteMenuById(Integer mid) {
        String sql = "delete from pn_admin_menu pam WHERE pam.mid = ?";
        Connection connection = DruidUtils.getConnection();
        Integer num = 0;
        try {
            num = new QueryRunner().update(connection, sql, mid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return num;
    }

    @Override
    public Integer editMenuById(Menu menu) {
        String sql = "UPDATE pn_admin_menu pam SET menuname = ?,pid=?,url=?,glyphicon=?,versionid=versionid + 1 WHERE pam.mid = ?";
        Connection connection = DruidUtils.getConnection();
        Integer num = 0;
        try {
            num = new QueryRunner().update(connection, sql, menu.getMenuname(),
                    menu.getPid(), menu.getUrl(), menu.getGlyphicon()
                    , menu.getMid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return num;
    }

    @Override
    public Menu regMenuByVersionId(Menu menu) {
        String sql = "select * from pn_admin_menu pam where pam.mid = ? and pam.versionid = ?";
        Connection connection = DruidUtils.getConnection();
        Menu menu1=null;
        try {
         menu1= new QueryRunner().query(connection,sql,new BeanHandler<>(Menu.class, new BasicRowProcessor(new GenerousBeanProcessor())), menu.getMid(),menu.getVersionid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return menu1;
    }

    @Override
    public List<Menu> getMenuByLevel(Integer level) {
        Connection connection = DruidUtils.getConnection();
        String sql = "SELECT * FROM pn_admin_menu pam WHERE ";
        if (level == 1) {
            //父级菜单
            sql += "pam.pid = 0 ";
        } else if (level == 2) {
            sql += "pam.pid !=0 ";
        }
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

}
