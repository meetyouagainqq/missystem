package com.javasm.system.controller;

import com.google.gson.Gson;
import com.javasm.system.dao.LoginDao;
import com.javasm.system.dao.impl.LoginDaoImpl;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.User;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;
import com.javasm.system.service.UserService;
import com.javasm.system.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/users/*")
public class UserController extends BaseController {
    private UserService userService = new UserServiceImpl();
    private LoginDao loginDao = new LoginDaoImpl();

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer page = 1;
        Integer pageSize = 3;
        String pageStr = req.getParameter("page");
        String pageSizeStr = req.getParameter("pageSize");
        if (pageStr != null && !"".equals(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        if (pageSizeStr != null && !"".equals(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        String username = req.getParameter("username");
        Integer uid = null;
        String uidStr = req.getParameter("uid");
        if (uidStr != null && !"".equals(uidStr)) {
            uid = Integer.parseInt(uidStr);
        }
        String isvalidStr = req.getParameter("isvalid");
        Integer isvalid = null;
        if (isvalidStr != null && !"".equals(isvalidStr)) {
            isvalid = Integer.parseInt(isvalidStr);
        }
        User user = new User();
        user.setUsername(username);
        user.setUid(uid);
        user.setIsvalid(isvalid);
        PageInfo pageInfo1 = new PageInfo();
        pageInfo1.setPage(page);
        pageInfo1.setPageSize(pageSize);
        List<User> userList = userService.getUserByPage(pageInfo1, user);
        Long num = userService.getTotalNum(user);
        pageInfo1.setTotalNum(num.intValue());
        R r = new R();
        if (userList.size() > 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(userList);
            r.setPageInfo(pageInfo1);
        } else {
            r.setCode(ResponseEnum.NO_DATA.getCode());
            r.setMsg(ResponseEnum.NO_DATA.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String roleIdStr = req.getParameter("roleId");
        Integer roleId = null;
        if (roleIdStr != null && !"".equals(roleIdStr)) {
            roleId = Integer.parseInt(roleIdStr);
        }
        String regTime = req.getParameter("regTime");
        String isvalidStr = req.getParameter("isvalid");
        Integer isvalid = null;
        if (isvalidStr != null && !"".equals(isvalidStr)) {
            isvalid = Integer.parseInt(isvalidStr);
        }
        Integer createUid = null;
        String createUidStr = req.getParameter("createUid");
        if (createUidStr != null && !"".equals(createUidStr)) {
            createUid = Integer.parseInt(createUidStr);
        }
        String remark = req.getParameter("remark");
        String headImg = req.getParameter("headImg");
        User user = new User(username, headImg, remark, roleId, regTime, isvalid, createUid);
        Integer num = userService.addUser(user);
        R r = new R();
        if (num > 0) {
            r.setCode(ResponseEnum.REQ_SUCCESS.getCode());
            r.setMsg(ResponseEnum.REQ_SUCCESS.getMsg());
        } else {
            r.setCode(ResponseEnum.REQ_FAILED.getCode());
            r.setMsg(ResponseEnum.REQ_FAILED.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }

    public void getUserForEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uidStr = req.getParameter("uid");
        Integer uid = null;
        if (uidStr != null && !"".equals(uidStr)) {
            uid = Integer.parseInt(uidStr);
        }
        User user = userService.getUserById(uid);
        R r = new R();
        if (user != null) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(user);
        } else {
            r.setCode(ResponseEnum.NO_DATA.getCode());
            r.setMsg(ResponseEnum.NO_DATA.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }

    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String roleIdStr = req.getParameter("roleId");
        String uidStr = req.getParameter("uid");
        Integer uid = null;
        if (uidStr != null && !"".equals(uidStr)) {
            uid = Integer.parseInt(uidStr);
        }
        Integer roleId = null;
        if (roleIdStr != null && !"".equals(roleIdStr)) {
            roleId = Integer.parseInt(roleIdStr);
        }
        String regTime = req.getParameter("regTime");
        String isvalidStr = req.getParameter("isvalid");
        Integer isvalid = null;
        if (isvalidStr != null && !"".equals(isvalidStr)) {
            isvalid = Integer.parseInt(isvalidStr);
        }
        String remark = req.getParameter("remark");
        String headImg = req.getParameter("headImg");
        User user = new User(username, headImg, remark, roleId, regTime, isvalid, null);
        user.setUid(uid);
        Integer num = userService.editUser(user);
        R r = new R();
        if (num > 0) {
            r.setCode(ResponseEnum.REQ_SUCCESS.getCode());
            r.setMsg(ResponseEnum.REQ_SUCCESS.getMsg());
        } else {
            r.setCode(ResponseEnum.REQ_FAILED.getCode());
            r.setMsg(ResponseEnum.REQ_FAILED.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uidStr = req.getParameter("uid");
        Integer uid = null;
        if (uidStr != null && !"".equals(uidStr)) {
            uid = Integer.parseInt(uidStr);
        }
        Integer num = userService.deleteUserById(uid);
        R r = new R();
        if (num > 0) {
            r.setCode(ResponseEnum.REQ_SUCCESS.getCode());
            r.setMsg(ResponseEnum.REQ_SUCCESS.getMsg());
        } else {
            r.setCode(ResponseEnum.REQ_FAILED.getCode());
            r.setMsg(ResponseEnum.REQ_FAILED.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }

    public void getAuthMenuList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer uid = null;
        String uidStr = req.getParameter("uid");
        if (uidStr != null && !"".equals(uidStr)) {
            uid = Integer.parseInt(uidStr);
        }
        //得到所有需要展示的菜单权限
        List<Menu> authMenuList = userService.getAuthMenuList();
        //用户已经拥有的权限id
        String userAuthId = loginDao.getUserMenuId(uid);
        R r = new R();
        if (authMenuList.size() > 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userAuthId", userAuthId);
            dataMap.put("authMenuList", authMenuList);
            r.setData(dataMap);
        }else {
            r.setCode(ResponseEnum.NO_DATA.getCode());
            r.setMsg(ResponseEnum.NO_DATA.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }
    public void editUserAuthList(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{
        String userAuthId = req.getParameter("userAuthId");
        Integer uid = null;
        String uidStr = req.getParameter("uid");
        if (uidStr != null && !"".equals(uidStr)) {
            uid = Integer.parseInt(uidStr);
        }
        Integer num = userService.editUserAuthId(userAuthId, uid);
        R r = new R();
        if(num>0){
            r.setCode(ResponseEnum.REQ_SUCCESS.getCode());
            r.setMsg(ResponseEnum.REQ_SUCCESS.getMsg());
        }else {
            r.setCode(ResponseEnum.REQ_FAILED.getCode());
            r.setMsg(ResponseEnum.REQ_FAILED.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }

}
