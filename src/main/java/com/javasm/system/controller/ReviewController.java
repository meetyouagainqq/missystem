package com.javasm.system.controller;

import com.google.gson.Gson;
import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.ProductBasic;
import com.javasm.system.entity.Review;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;
import com.javasm.system.service.ProductBasicService;
import com.javasm.system.service.ReviewService;
import com.javasm.system.service.impl.ProductBasicServiceImpl;
import com.javasm.system.service.impl.ReviewServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/company/review/*")
public class ReviewController extends BaseController{
    private ReviewService reviewService=new ReviewServiceImpl();
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
        //查询所用的参数
        String pname = req.getParameter("pname");
        //审核状态
        Integer status = null;
        String statusStr = req.getParameter("status");
        if (statusStr != null && !"".equals(statusStr)) {
            status = Integer.parseInt(statusStr);
        }
        Review review = new Review(pname, status);
        List<Review> reviewList = reviewService.getProductListByQuery(page,pageSize,review);
        //查询个数
        Long num = reviewService.getNumByQuery(review);
        R r = new R();
        if (reviewList.size() > 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(reviewList);
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
}
