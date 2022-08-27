package com.javasm.system.controller;

import com.google.gson.Gson;
import com.javasm.system.dao.impl.ProductRecommendImpl;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void dataForRel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ProductRecommendService prs=new ProductRecommendServiceImpl();

        List<ProductRecommend> productRecommends = prs.dataForRel();



        R r = new R();
        if (productRecommends.size()> 0) {
            r.setCode(ResponseEnum.REQ_SUCCESS.getCode());
            r.setMsg(ResponseEnum.REQ_SUCCESS.getMsg());
            r.setData(productRecommends);

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










        public void  EditProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过id修改的接口
        String PidStr = req.getParameter("id");
        String finerStr = req.getParameter("finer");
        String visibleStr = req.getParameter("visible");
        String issueStr = req.getParameter("issue");
        String subscribeStr = req.getParameter("subscribe");
        String explain = req.getParameter("explain");

        Integer Pid = null;
        Integer finer = null;
        Integer visible = null;
        Integer issue = null;
        Integer subscribe = null;


        if (PidStr != null && !"".equals(PidStr)) {
            Pid = Integer.parseInt(PidStr);
        }
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


        ProductRecommend editPro = new ProductRecommend(Pid, finer, visible, issue, subscribe, explain);

        ProductRecommendService prs=new ProductRecommendServiceImpl();
        Integer integer = prs.EditProduct(editPro);
        //Long allProductNum = prs.getAllProductNum();

        R r = new R();
        if (integer> 0) {
            r.setCode(ResponseEnum.REQ_SUCCESS.getCode());
            r.setMsg(ResponseEnum.REQ_SUCCESS.getMsg());
            r.setData(editPro);
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


    public void getProductForEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//通过pid查询数据
        String pidStr = req.getParameter("id");
        Integer pid=null;
        if(pidStr!=null&&!"".equals(pidStr)){
            pid = Integer.parseInt(pidStr);
        }

        ProductRecommendService prs=new ProductRecommendServiceImpl();
        ProductRecommend productById = prs.getProductById(pid);

        R r = new R();
        if (productById!=null) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(productById);

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

    public void getProductByPName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //模糊查询的接口

        Integer page=1;
        Integer pageSize=3;

        String pname = req.getParameter("pname");
        String pageStr = req.getParameter("page");
        String pageSizeStr = req.getParameter("pageSize");
        //转换数据类型
        if(pageStr!=null&&!"".equals(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(pageSizeStr!=null&&!"".equals(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }




        //调用service
        ProductRecommendService prs =new ProductRecommendServiceImpl();
        List<ProductRecommend> prod=prs.getProductByPName(page,pageSize,pname);
        Long allProductNum = prs.getAllProductNum(pname);

        R r = new R();
        if (prod.size()> 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(prod);
            PageInfo pif=new PageInfo();
            pif.setPage(page);
            pif.setPageSize(pageSize);
            pif.setTotalNum(allProductNum.intValue());
            r.setPageInfo(pif);

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

    public void getDropDownList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取添加下拉菜单的接口
        List<ProductRecommend>prod=new ProductRecommendServiceImpl().getDropDownList();

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



    public void getAllProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//分页查询
        Integer page=1;
        Integer pageSize=3;
       Integer totalNum=10;
        String pageStr = req.getParameter("page");
        String pageSizeStr = req.getParameter("pageSize");
        String totalNumStr = req.getParameter("totalNum");
       String name = req.getParameter("name");

        //转换数据类型
        if(pageStr!=null&&!"".equals(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(pageSizeStr!=null&&!"".equals(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        if(totalNumStr!=null&&!"".equals(totalNumStr)){
            totalNum = Integer.parseInt(totalNumStr);
        }


        //查询使用的菜单对象
        //ProductRecommend product=new ProductRecommend();

        //调用service
         ProductRecommendService prs =new ProductRecommendServiceImpl();
         List<ProductRecommend> prod =prs.getPaging(page,pageSize);
        //查询总记录数

        Long allProductNum = prs.getAllProductNum(name);
        System.out.println(allProductNum);
        R r = new R();
        if (prod.size()> 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(prod);
            PageInfo pif=new PageInfo();
            pif.setPage(page);
            pif.setPageSize(pageSize);
            pif.setTotalNum(allProductNum.intValue());

            //pif.setTotalNum(allProductNum.intValue());

            r.setPageInfo(pif);
            System.out.println(pif);

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
        Integer id = 0;

        String finerStr = req.getParameter("finer");
        String visibleStr = req.getParameter("visible");
        String issueStr = req.getParameter("issue");
        String subscribeStr = req.getParameter("subscribe");
        String explain = req.getParameter("explain");
        String idStr = req.getParameter("pname");

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
        if (idStr != null && !"".equals(idStr)) {
            id = Integer.parseInt(idStr);
        }


        //构建对象
        ProductRecommend pro = new ProductRecommend(id,finer, visible, issue, subscribe, explain);

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

    public void getAllProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//查询菜单的接口


        List<ProductRecommend> prod = new ProductRecommendServiceImpl().getAllProducts();

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
