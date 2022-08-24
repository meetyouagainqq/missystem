package com.javasm.system.controller;

import com.google.gson.Gson;
import com.javasm.system.entity.Menu;
import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.ProductRecommend;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;
import com.javasm.system.service.ProductRecommendService;
import com.javasm.system.service.impl.ProductRecommendServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.List;

/**
 * @author:Dai
 * @className:ProductRecommendController
 * @description:
 * @date:2022/8/23 11:05
 * @version: 1.0
 * @since: jdk11
 */
@WebServlet("/productRecommend/*")
public class ProductRecommendController extends BaseController {


    public void getAllProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer page=1;
        Integer pageSize=3;

        String pageStr = req.getParameter("page");
        String pageSizeStr = req.getParameter("pageSize");

        //转换数据类型
        if(pageStr!=null&&!"".equals(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(pageSizeStr!=null&&!"".equals(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        //查询使用的菜单对象
        ProductRecommend product=new ProductRecommend();

        //调用service
         ProductRecommendService prs =new ProductRecommendServiceImpl();
         List<ProductRecommend>prod=prs.getAllProduct(page,pageSize);
        //查询总记录数

        Long allProductNum = prs.getAllProductNum();

        R r = new R();
        if (prod.size()> 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(prod);
            PageInfo pif=new PageInfo();
            pif.setPage(page);
            pif.getPageSize(pageSize);
            pif.setTotalNum(allProductNum.intValue());
            r.setPageInfo(pif);

        } else {
            r.setCode(ResponseEnum.NO_DATA.getCode());
            r.setMsg(ResponseEnum.NO_DATA.getMsg());
        }

//返回数据
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();


    }





    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //添加的服务接口

        Integer finer = 0;
        Integer visible = 0;
        Integer issue = 0;
        Integer subscribe = 0;

        String finerStr = req.getParameter("finer");
        String visibleStr = req.getParameter("visible");
        String issueStr = req.getParameter("issue");
        String subscribeStr = req.getParameter("subscribe");
        String explain = req.getParameter("explain");

        //转换数据类型
        if (finerStr != null && !"".equals(finerStr)) {
            finer = Integer.parseInt(finerStr);
        }

        if (visibleStr != null && !"".equals(visibleStr)) {
            visible = Integer.parseInt(visibleStr);
        }

        if (issueStr != null && !"".equals(issueStr)) {
            issue = Integer.parseInt(issueStr);
        }

        if (subscribeStr != null && !"".equals(subscribeStr)) {
            subscribe = Integer.parseInt(subscribeStr);
        }


        //构建对象
        ProductRecommend pro = new ProductRecommend(finer, visible, issue, subscribe, explain);

        //直接调用
        ProductRecommendService mrs = new ProductRecommendServiceImpl();
        Integer integer = mrs.addProd(pro);

        //根据执行结果返回数据
        R r = new R();
        if (integer > 0) {
            r.setCode(ResponseEnum.REQ_SUCCESS.getCode());
            r.setMsg(ResponseEnum.REQ_SUCCESS.getMsg());

        } else {
            r.setCode(ResponseEnum.REQ_FAILED.getCode());
            r.setMsg(ResponseEnum.REQ_FAILED.getMsg());
        }

//返回数据
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();


    }

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<ProductRecommend> prod = new ProductRecommendServiceImpl().query();

        R r = new R();
        if (prod.size() > 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(prod);
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


}
