package com.javasm.system.controller;

import com.google.gson.Gson;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;
import com.javasm.system.service.MenuService;
import com.javasm.system.service.impl.MenuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/menus/*")
public class MenuController extends BaseController {
    private MenuService menuService = new MenuServiceImpl();

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer page = 1;
        Integer pageSize = 10;
        String pageStr = req.getParameter("page");
        String pageSizeStr = req.getParameter("pageSize");
        if (pageStr != null && !"".equals(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        if (pageSizeStr != null && !"".equals(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        String menuname = req.getParameter("menuname");
        Integer pid = null;
        String pidStr = req.getParameter("pid");
        if (pidStr != null && !"".equals(pidStr)) {
            pid = Integer.parseInt(pidStr);
        }
        Menu menuParam = new Menu(menuname, pid);
        List<Menu> menuList = menuService.getAllMenus(page, pageSize, menuParam);
        Long num = menuService.getMenuNumByQuery(menuParam);
        R r = new R();
        if (menuList.size() > 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(menuList);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPage(page);
            pageInfo.setPageSize(pageSize);
            pageInfo.setTotalNum(num.intValue());
            r.setPageInfo(pageInfo);
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

    public void getMenuSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Menu> menuList = menuService.getMenuForSelect();
        R r = new R();
        if (menuList.size() > 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(menuList);
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
        String midStr = req.getParameter("mid");
        String menuname = req.getParameter("menuname");
        String pidStr = req.getParameter("pid");
        String url = req.getParameter("url");
        String glyphicon = req.getParameter("glyphicon");
        Integer mid = null;
        if (midStr != null && !"".equals(midStr)) {
            mid = Integer.parseInt(midStr);
        }
        Integer pid = null;
        if (pidStr != null && !"".equals(pidStr)) {
            pid = Integer.parseInt(pidStr);
        }
        Integer num = menuService.addMenu(new Menu(mid, menuname, pid, url, glyphicon));
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

    public void getMenuForEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String midStr = req.getParameter("mid");
        Integer mid = null;
        if (midStr != null && !"".equals(midStr)) {
            mid = Integer.parseInt(midStr);
        }
        Menu menu = menuService.getMenuById(mid);
        R r = new R();
        if (menu != null) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(menu);
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

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String midStr = req.getParameter("mid");
        Integer mid = null;
        if (midStr != null && !"".equals(midStr)) {
            mid = Integer.parseInt(midStr);
        }
        Integer num = menuService.deleteMenuById(mid);
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

    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String midStr = req.getParameter("mid");
        String menuname = req.getParameter("menuname");
        String pidStr = req.getParameter("pid");
        String url = req.getParameter("url");
        String glyphicon = req.getParameter("glyphicon");
        String versionidStr = req.getParameter("versionid");
        Integer versionid = 0;
        if (versionidStr != null && !"".equals(versionidStr)) {
            versionid = Integer.parseInt(versionidStr);
        }
        Integer mid = null;
        if (midStr != null && !"".equals(midStr)) {
            mid = Integer.parseInt(midStr);
        }
        Integer pid = null;
        if (pidStr != null && !"".equals(pidStr)) {
            pid = Integer.parseInt(pidStr);
        }
        Menu menu = new Menu();
        menu.setPid(pid);
        menu.setMid(mid);
        menu.setMenuname(menuname);
        menu.setUrl(url);
        menu.setGlyphicon(glyphicon);
        menu.setVersionid(versionid);
        //通过检查版本号，查看数据是否被修改过
        Menu menu1 = menuService.regMenuByVersionId(menu);
        R r = new R();
        if (menu1 != null) {
            Integer num = menuService.editMenuById(menu);
            if (num > 0) {
                r.setCode(ResponseEnum.REQ_SUCCESS.getCode());
                r.setMsg(ResponseEnum.REQ_SUCCESS.getMsg());
            } else {
                r.setCode(ResponseEnum.REQ_FAILED.getCode());
                r.setMsg(ResponseEnum.REQ_FAILED.getMsg());
            }
        } else {
            r.setCode(ResponseEnum.DATA_ALREADY_CHANGE.getCode());
            r.setMsg(ResponseEnum.DATA_ALREADY_CHANGE.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }
}
