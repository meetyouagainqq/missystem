package com.javasm.system.service.impl;

import com.javasm.system.dao.LoginDao;
import com.javasm.system.dao.impl.LoginDaoImpl;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.User;
import com.javasm.system.service.LoginService;

import java.util.ArrayList;
import java.util.List;

public class LoginServiceImpl implements LoginService {
    private LoginDao loginDao = new LoginDaoImpl();

    @Override
    public User loginUser(User user) {
        User loginUser = loginDao.loginUser(user);
        return loginUser;
    }

    @Override
    public List<Menu> getMenusById(Integer userId) {
        String menuIds = loginDao.getUserMenuId(userId);
        //一级菜单列表
        List<Menu> parentMenuList = loginDao.getMenuListByLevel(menuIds, 1);
        //二级菜单列表
        List<Menu> childrenList = loginDao.getMenuListByLevel(menuIds, 2);
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

    @Override
    public List<String> getMenuUrlById(Integer userId) {
        String menuIds = loginDao.getUserMenuId(userId);
        List<String> url = loginDao.getMenuUrlById(menuIds);
        return url;
    }


}
