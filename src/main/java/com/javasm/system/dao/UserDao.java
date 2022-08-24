package com.javasm.system.dao;

import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.User;

import java.util.List;

public interface UserDao {
    User getUserById(Integer uid);

    List<User> getUserByPage(PageInfo pageInfo, User queryParam);

    Long getTotalNum(User queryParam);

    Integer addUser(User user);

    Integer editUser(User user);
    Integer deleteUserById(Integer uid);
    Integer editUserAuthId(String authIdStr,Integer uid);
}
