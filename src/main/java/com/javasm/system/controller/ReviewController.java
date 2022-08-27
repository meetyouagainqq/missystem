package com.javasm.system.controller;

import com.google.gson.Gson;
import com.javasm.system.entity.PageInfo;
import com.javasm.system.entity.ProductBasic;
import com.javasm.system.entity.Review;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;
import com.javasm.system.service.ReviewService;
import com.javasm.system.service.impl.ReviewServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/company/review/*")
public class ReviewController extends BaseController {
    private ReviewService reviewService = new ReviewServiceImpl();

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
        List<Review> reviewList = reviewService.getProductListByQuery(page, pageSize, review);
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

    public void getReviewForEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Integer id = null;
        if (idStr != null && !"".equals(idStr)) {
            id = Integer.parseInt(idStr);
        }
        ProductBasic basic= reviewService.getBasicById(id);
        R r = new R();
        if (basic != null) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(basic);
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
    public void rollBackStatus(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String idStr = req.getParameter("id");
        Integer id = null;
        if (idStr != null && !"".equals(idStr)) {
            id = Integer.parseInt(idStr);
        }
        Integer num = reviewService.rollbackStatus(id);
        R r = new R();
        if (num>0) {
            r.setCode(ResponseEnum.UPDATE_SUCCESS.getCode());
            r.setMsg(ResponseEnum.UPDATE_SUCCESS.getMsg());
        } else {
            r.setCode(ResponseEnum.UPDATE_FAILED.getCode());
            r.setMsg(ResponseEnum.UPDATE_FAILED.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }
    public void reviewStatus(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String idStr = req.getParameter("id");
        Integer id = null;
        if (idStr != null && !"".equals(idStr)) {
            id = Integer.parseInt(idStr);
        }
        Integer num = reviewService.reviewStatus(id);
        R r = new R();
        if (num>0) {
            r.setCode(ResponseEnum.REVIEW_SUCCESS.getCode());
            r.setMsg(ResponseEnum.REVIEW_SUCCESS.getMsg());
        } else {
            r.setCode(ResponseEnum.REVIEW_FAILED.getCode());
            r.setMsg(ResponseEnum.REVIEW_FAILED.getMsg());
        }
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }
}
