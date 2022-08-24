package com.javasm.system.dao;

import com.javasm.system.entity.Menu;
import com.javasm.system.entity.User;

import java.util.List;

public interface LoginDao {
    User loginUser(User user);
    String getUserMenuId(Integer userId);
    List<Menu> getMenuListByLevel(String menuIds,Integer level);
    List<String> getMenuUrlById(String menuIds);
}
