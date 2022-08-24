package com.javasm.system.controller.series;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.javasm.system.controller.BaseController;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.Series;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;
import com.javasm.system.service.series.SeriesService;
import com.javasm.system.service.series.impl.SeriesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author: LinHai
 * @className: SeriesController
 * @Description:
 * @date: 2022/8/23 16:15
 * @version: 0.1
 * @since: jdk11
 */
@WebServlet("/series/*")
public class SeriesController extends BaseController {

    public void getSeriesSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //步骤1
        //接收请求参数 cookie参数 请求头传参数
        //转换数据类型
        //封装数据对象
//        req.setCharacterEncoding("utf-8");
//        String username = req.getParameter("username");
//        String userpass = req.getParameter("userpass");
//
//        System.out.println(username+"----"+userpass);
//
//        MyUser insertUser = new MyUser(username, userpass);
//        //步骤2 调用业务逻辑
//        LoginService ls = new LoginServiceImpl();
//        MyUser loginUser = ls.login(insertUser);


        Integer page = 1;
        Integer pageSize = 10;

        String pageStr = req.getParameter("page");
        String pageSizeStr = req.getParameter("pageSize");

        //如果传了 使用传的值 如果没传 使用默认值
        if(pageStr!=null&&!"".equals(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(pageSizeStr!=null&&!"".equals(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }


        SeriesServiceImpl seriesService = new SeriesServiceImpl();
        List<Series> series = seriesService.getSeries(page,pageSize);
        //步骤3 根据执行结果不同 反馈不同的数据
        //查询总记录数
        Long allSeriesNum = seriesService.getAllSeriesNum();


        R r = new R();
        if(series.size()>0){

            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(series);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPage(page);
            pageInfo.setPageSize(pageSize);
            pageInfo.setTotalNum(allSeriesNum.intValue());
            r.setPageInfo(pageInfo);

        }else{
            //登录失败  用户名或密码错误
            r.setCode(ResponseEnum.NO_DATA.getCode());
            r.setMsg(ResponseEnum.NO_DATA.getMsg());

        }

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSONString(r));
        writer.flush();
        writer.close();
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        private Integer sid;
//        private String sname;
//        private String sename;
//        private String remittance;
        String sidStr = req.getParameter("sid");
        String sname = req.getParameter("sname");
        String sename = req.getParameter("sename");
        String remittance = req.getParameter("remittance");

        Integer sid = null;
        if (sidStr != null && !"".equals(sidStr)) {
            sid = Integer.parseInt(sidStr);
        }
        SeriesServiceImpl seriesService = new SeriesServiceImpl();
        Integer num = seriesService.addSeries(new Series(sid, sname,sename, remittance));
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


        System.out.println("执行添加");

    }

}
