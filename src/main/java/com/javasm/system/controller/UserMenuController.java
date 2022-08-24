package com.javasm.system.controller;

import com.google.gson.Gson;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.User;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/getUserMenu")
public class UserMenuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        List<Menu> menuList = (List<Menu>) session.getAttribute("menuList");
        R r = new R();
        if(menuList!=null){
            r.setCode(ResponseEnum.REQ_SUCCESS.getCode());
            r.setMsg(ResponseEnum.REQ_SUCCESS.getMsg());
            Map<String,Object> dataMap=new HashMap<>();
            dataMap.put("loginUser",loginUser);
            dataMap.put("menuList",menuList);
            r.setData(dataMap);
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
