package com.javasm.system.service;

import com.javasm.system.entity.Menu;
import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer uid);
    List<User> getUserByPage(PageInfo pageInfo, User queryParam);
    Long getTotalNum(User queryParam);
    Integer addUser(User user);
    Integer editUser(User user);
    Integer deleteUserById(Integer uid);
    Integer editUserAuthId(String authIdStr,Integer uid);
    List<Menu> getAuthMenuList();
}
