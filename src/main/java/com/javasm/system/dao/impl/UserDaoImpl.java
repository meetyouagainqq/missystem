package com.javasm.system.dao.impl;

import com.javasm.system.dao.UserDao;
import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.User;
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

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserById(Integer uid) {
        String sql = "SELECT * FROM pn_admin_user pau WHERE pau.uid= ?";
        Connection connection = DruidUtils.getConnection();
        User loginUser = null;
        try {
            loginUser = new QueryRunner().query(connection, sql, new BeanHandler<>(User.class, new BasicRowProcessor(new GenerousBeanProcessor()))
                    , uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return loginUser;
    }

    @Override
    public List<User> getUserByPage(PageInfo pageInfo, User queryParam) {
        String sql = "SELECT * FROM pn_admin_user pau";
        Connection connection = DruidUtils.getConnection();
        List<User> userList = new ArrayList<>();
        List<Object> paramList = new ArrayList<>();
        boolean flag = true;
        if (queryParam.getUsername() != null && !"".equals(queryParam.getUsername())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pau.username like ? ";
            paramList.add("%" + queryParam.getUsername() + "%");
        }
        if (queryParam.getUid()!= null && !"".equals(queryParam.getUid())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pau.uid = ? ";
            paramList.add(queryParam.getUid());
        }
        if (queryParam.getIsvalid()!= null && !"".equals(queryParam.getIsvalid())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pau.isvalid = ? ";
            paramList.add(queryParam.getIsvalid());
        }
        sql += " limit " + (pageInfo.getPage() - 1) * pageInfo.getPageSize() + "," + pageInfo.getPageSize();
        try {
            userList = new QueryRunner().query(connection, sql, new BeanListHandler<>(User.class, new BasicRowProcessor(new GenerousBeanProcessor())), paramList.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return userList;
    }

    @Override
    public Long getTotalNum(User queryParam) {
        String sql = "SELECT COUNT(1) AS totalNum FROM pn_admin_user pau";
        Connection connection = DruidUtils.getConnection();
        List<User> userList = new ArrayList<>();
        List<Object> paramList = new ArrayList<>();
        boolean flag = true;
        if (queryParam.getUsername() != null && !"".equals(queryParam.getUsername())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pau.username like ? ";
            paramList.add("%" + queryParam.getUsername() + "%");
        }
        if (queryParam.getUid()!= null && !"".equals(queryParam.getUid())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pau.uid = ? ";
            paramList.add(queryParam.getUid());
        }
        if (queryParam.getIsvalid()!= null && !"".equals(queryParam.getIsvalid())) {
            if (flag) {
                sql += " where ";
                flag = false;
            } else {
                sql += " and ";
            }
            sql += " pau.isvalid = ? ";
            paramList.add(queryParam.getIsvalid());
        }
        Long totalNum=0L;
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
    public Integer addUser(User user) {
        String sql = "INSERT INTO pn_admin_user (username,password,role_id,reg_time,isvalid,create_uid,remark,head_img) VALUES (?,?,?,?,?,?,?,?)";
        Connection connection = DruidUtils.getConnection();
        Integer addNum = 0;
        try {
            addNum = new QueryRunner().update(connection, sql, user.getUsername(),user.getPassword(),user.getRoleId(),user.getRegTime()
            ,user.getIsvalid(),user.getCreateUid(),user.getRemark(),user.getHeadImg());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return addNum;
    }

    @Override
    public Integer editUser(User user) {
        String sql = "UPDATE pn_admin_user pau SET username = ?,role_id=?,reg_time=?,isvalid=?,remark=?,head_img=? WHERE pau.uid = ?";
        Connection connection = DruidUtils.getConnection();
        Integer num = 0;
        try {
            num = new QueryRunner().update(connection, sql,user.getUsername(),user.getRoleId(),user.getRegTime(),user.getIsvalid()
            ,user.getRemark(),user.getHeadImg(),user.getUid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return num;
    }

    @Override
    public Integer deleteUserById(Integer uid) {
        String sql = "delete from pn_admin_user pau WHERE pau.uid = ?";
        Connection connection = DruidUtils.getConnection();
        Integer num = 0;
        try {
            num = new QueryRunner().update(connection, sql, uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return num;
    }

    @Override
    public Integer editUserAuthId(String authIdStr, Integer uid) {
        String sql = "UPDATE pn_admin_user pau SET menu_id = ? WHERE pau.uid = ?";
        Connection connection = DruidUtils.getConnection();
        Integer num = 0;
        try {
            num = new QueryRunner().update(connection, sql,authIdStr,uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtils.close(connection);
        }
        return num;
    }
}
