package com.javasm.system.service;

import com.javasm.system.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> getAllMenus(Integer page, Integer pageSize, Menu queryMenu);

    public Long getMenuNumByQuery(Menu queryMenu);

    public List<Menu> getMenuForSelect();

    public Integer addMenu(Menu menu);

    public Menu getMenuById(Integer mid);

    public Integer deleteMenuById(Integer mid);

    public Integer editMenuById(Menu menu);

    public Menu regMenuByVersionId(Menu menu);
}
