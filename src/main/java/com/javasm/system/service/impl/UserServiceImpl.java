package com.javasm.system.service.impl;

import com.javasm.system.dao.MenuDao;
import com.javasm.system.dao.UserDao;
import com.javasm.system.dao.impl.MenuDaoImpl;
import com.javasm.system.dao.impl.UserDaoImpl;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.User;
import com.javasm.system.service.MenuService;
import com.javasm.system.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private MenuDao menuDao=new MenuDaoImpl();

    @Override
    public User getUserById(Integer uid) {
        User user = userDao.getUserById(uid);
        return user;
    }

    @Override
    public List<User> getUserByPage(PageInfo pageInfo, User queryParam) {
        List<User> userList = userDao.getUserByPage(pageInfo, queryParam);
        return userList;
    }

    @Override
    public Long getTotalNum(User queryParam) {
        Long totalNum = userDao.getTotalNum(queryParam);
        return totalNum;
    }

    @Override
    public Integer addUser(User user) {
        Integer num = userDao.addUser(user);
        return num;
    }

    @Override
    public Integer editUser(User user) {
        Integer num = userDao.editUser(user);
        return num;
    }

    @Override
    public Integer deleteUserById(Integer uid) {
        Integer num = userDao.deleteUserById(uid);
        return num;
    }

    @Override
    public Integer editUserAuthId(String authIdStr, Integer uid) {
        Integer num = userDao.editUserAuthId(authIdStr, uid);
        return num;
    }

    @Override
    public List<Menu> getAuthMenuList() {
        List<Menu> parentMenuList = menuDao.getMenuByLevel(1);
        List<Menu> childrenList = menuDao.getMenuByLevel(2);
        for (Menu parentMenu : parentMenuList) {
            for (Menu childrenMenu : childrenList) {
                if (childrenMenu.getPid().equals(parentMenu.getMid())) {
                    List<Menu> subMenu = parentMenu.getSubMenu();
                    if (subMenu == null) {
                        subMenu = new ArrayList<Menu>();
                    }
                    subMenu.add(childrenMenu);
                    parentMenu.setSubMenu(subMenu);
                }
            }
        }
        return parentMenuList;
    }
}
