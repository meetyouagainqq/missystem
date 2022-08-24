package com.javasm.system.dao;

import com.javasm.system.entity.Menu;

import java.util.List;

public interface MenuDao {
    public List<Menu> getAllMenus(Integer page, Integer pageSize, Menu queryMenu);
    public Long getMenuNumByQuery(Menu queryMenu);
    public List<Menu> getMenuForSelect();
    public Integer addMenu(Menu menu);
    public Menu getMenuById(Integer mid);
    public Integer deleteMenuById(Integer mid);
    public Integer editMenuById(Menu menu);
    public Menu regMenuByVersionId(Menu menu);
   public List<Menu> getMenuByLevel(Integer level);
}
