package com.javasm.system.filter;

import com.google.gson.Gson;
import com.javasm.system.entity.User;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


//@WebFilter("/*")
public class MyFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String reqServletPath = req.getServletPath();
        if ("/login".equals(reqServletPath)) {
            filterChain.doFilter(servletRequest,servletResponse);

        }else {
            if(loginUser!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                R r = new R();
                r.setCode(ResponseEnum.NO_LOGIN.getCode());
                r.setMsg(ResponseEnum.NO_LOGIN.getMsg());
                resp.setContentType("application/json;charset=utf-8");
                Gson gson = new Gson();
                PrintWriter writer = resp.getWriter();
                writer.print(gson.toJson(r));
                writer.flush();
                writer.close();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
