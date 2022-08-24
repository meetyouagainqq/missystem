package com.javasm.system.service;

import com.javasm.system.entity.Menu;
import com.javasm.system.entity.User;

import java.util.List;

public interface LoginService {
    User loginUser(User user);
     List<Menu> getMenusById(Integer userId);
    public List<String> getMenuUrlById(Integer userId);
}
