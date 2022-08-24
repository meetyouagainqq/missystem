package com.javasm.system.controller;


import com.google.gson.Gson;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.User;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;
import com.javasm.system.service.LoginService;
import com.javasm.system.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin","http://localhost:8100");
        /* 允许跨域的请求⽅法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //System.out.println(username+":"+password);
        LoginService loginService=new LoginServiceImpl();
        User user = new User(username,password);
        User loginUser = loginService.loginUser(user);
        R r = new R();
        if(loginUser!=null){
            HttpSession session = req.getSession();
            session.setAttribute("loginUser",loginUser);
            //查询用户的权限菜单
            List<Menu> menuList = loginService.getMenusById(loginUser.getUid());
            session.setAttribute("menuList",menuList);
            List<String> urlList = loginService.getMenuUrlById(loginUser.getUid());
            session.setAttribute("urlList",urlList);
            r.setCode(ResponseEnum.LOGIN_SUCCESS.getCode());
            r.setMsg(ResponseEnum.LOGIN_SUCCESS.getMsg());
        }else {
            r.setCode(ResponseEnum.LOGIN_ERROR.getCode());
            r.setMsg(ResponseEnum.LOGIN_ERROR.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }
}
