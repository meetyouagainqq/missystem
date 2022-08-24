package com.javasm.system.service.impl;

import com.javasm.system.dao.MenuDao;
import com.javasm.system.dao.impl.MenuDaoImpl;
import com.javasm.system.entity.Menu;
import com.javasm.system.service.MenuService;

import java.util.List;

public class MenuServiceImpl implements MenuService {
   private MenuDao menuDao = new MenuDaoImpl();

    @Override
    public List<Menu> getAllMenus(Integer page, Integer pageSize, Menu queryMenu) {
        List<Menu> menuList = menuDao.getAllMenus(page, pageSize, queryMenu);
        return menuList;
    }

    @Override
    public Long getMenuNumByQuery(Menu queryMenu) {
        Long num = menuDao.getMenuNumByQuery(queryMenu);
        return num;
    }

    @Override
    public List<Menu> getMenuForSelect() {
        List<Menu> menuList = menuDao.getMenuForSelect();
        return menuList;
    }

    @Override
    public Integer addMenu(Menu menu) {
        Integer num = menuDao.addMenu(menu);
        return num;
    }

    @Override
    public Menu getMenuById(Integer mid) {
        Menu menu = menuDao.getMenuById(mid);
        return menu;
    }

    @Override
    public Integer deleteMenuById(Integer mid) {
        Integer num = menuDao.deleteMenuById(mid);
        return num;
    }

    @Override
    public Integer editMenuById(Menu menu) {
        Integer num = menuDao.editMenuById(menu);
        return num;
    }

    @Override
    public Menu regMenuByVersionId(Menu menu) {
        Menu menu1 = menuDao.regMenuByVersionId(menu);
        return menu1;
    }


}
