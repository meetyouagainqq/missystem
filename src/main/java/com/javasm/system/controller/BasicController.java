package com.javasm.system.controller;

import com.google.gson.Gson;
import com.javasm.system.entity.*;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;
import com.javasm.system.service.ProductBasicService;
import com.javasm.system.service.impl.ProductBasicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

/*
 **产品基础信息
 */
@WebServlet("/company/basic/*")
public class BasicController extends BaseController {
    private ProductBasicService basicService = new ProductBasicServiceImpl();

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
        //产品类型
        String classificationStr = req.getParameter("classification");
        Integer classification = null;
        if (classificationStr != null && !"".equals(classificationStr)) {
            classification = Integer.parseInt(classificationStr);
        }
        //审核状态
        Integer status = null;
        String statusStr = req.getParameter("status");
        if (statusStr != null && !"".equals(statusStr)) {
            status = Integer.parseInt(statusStr);
        }
        ProductBasic queryProduct = new ProductBasic(pname, classification, status);
        List<ProductBasic> basicList = basicService.getProductListByQuery(page, pageSize, queryProduct);
        //查询个数
        Long num = basicService.getBasicNumByQuery(queryProduct);
        R r = new R();
        if (basicList.size() > 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(basicList);
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

    public void getProductSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductType> productSelect = basicService.getProductSelect();
        R r = new R();
        if (productSelect.size() > 0) {
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(productSelect);
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

    public void addBasic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pname = req.getParameter("pname");
        String seriesStr = req.getParameter("series");
        Integer series = null;
        if (seriesStr != null && !"".equals(seriesStr)) {
            series = Integer.parseInt(seriesStr);
        }
        Integer classification = null;
        String classificationStr = req.getParameter("classification");
        if (classificationStr != null && !"".equals(classificationStr)) {
            classification = Integer.parseInt(classificationStr);
        }
        String mechanismName = req.getParameter("mechanismName");
        String openTime = req.getParameter("openTime");
        String currency = req.getParameter("currency");
        Integer status = 1;
        String statusStr = req.getParameter("status");
        if (statusStr != null && !"".equals(statusStr)) {
            status = Integer.parseInt(statusStr);
        }
        String yield = req.getParameter("yield");
        yield = yield + "%";
        String reviewPerson = req.getParameter("reviewPerson");
        String cycle = req.getParameter("cycle");
        String managementRate = req.getParameter("managementRate");
        managementRate = managementRate + "%";
        String subscriptionRate = req.getParameter("subscriptionRate");
        subscriptionRate = subscriptionRate + "%";
        String amountStr = req.getParameter("amount");
        BigDecimal amount = new BigDecimal(amountStr);
        if (amountStr != null && !"".equals(amountStr)) {
            //设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
            amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        String method = req.getParameter("method");
        String redemptionPeriod = req.getParameter("redemptionPeriod");
        String startingAmountStr = req.getParameter("startingAmount");
        BigDecimal startingAmount = new BigDecimal(startingAmountStr);
        if (startingAmountStr != null && !"".equals(startingAmountStr)) {
            startingAmount = startingAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        String redemptionFeeStr = req.getParameter("redemptionFee");
        BigDecimal redemptionFee = new BigDecimal(redemptionFeeStr);
        if (redemptionFeeStr != null && !"".equals(redemptionFeeStr)) {
            redemptionFee = redemptionFee.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        String lockPeriod = req.getParameter("lockPeriod");
        Integer num = basicService.addProduct(new ProductBasic(pname, series, classification, mechanismName, openTime, currency, status, yield, reviewPerson,
                cycle, managementRate, subscriptionRate, amount, method, redemptionPeriod, startingAmount, redemptionFee, lockPeriod));
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

    public void getBasicForEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Integer id = null;
        if (idStr != null && !"".equals(idStr)) {
            id = Integer.parseInt(idStr);
        }
        ProductBasic basic = basicService.getBasicById(id);
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

    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Integer id = null;
        if (idStr != null && !"".equals(idStr)) {
            id = Integer.parseInt(idStr);
        }
        String pname = req.getParameter("pname");
        String seriesStr = req.getParameter("series");
        Integer series = null;
        if (seriesStr != null && !"".equals(seriesStr)) {
            series = Integer.parseInt(seriesStr);
        }
        Integer classification = null;
        String classificationStr = req.getParameter("classification");
        if (classificationStr != null && !"".equals(classificationStr)) {
            classification = Integer.parseInt(classificationStr);
        }
        String mechanismName = req.getParameter("mechanismName");
        String openTime = req.getParameter("openTime");
        String currency = req.getParameter("currency");
        Integer status = 1;
        String statusStr = req.getParameter("status");
        if (statusStr != null && !"".equals(statusStr)) {
            status = Integer.parseInt(statusStr);
        }
        String yield = req.getParameter("yield");
        yield = yield + "%";
        String reviewPerson = req.getParameter("reviewPerson");
        String cycle = req.getParameter("cycle");
        String managementRate = req.getParameter("managementRate");
        managementRate = managementRate + "%";
        String subscriptionRate = req.getParameter("subscriptionRate");
        subscriptionRate = subscriptionRate + "%";
        String amountStr = req.getParameter("amount");
        BigDecimal amount = new BigDecimal(amountStr);
        if (amountStr != null && !"".equals(amountStr)) {
            //设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
            amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        String method = req.getParameter("method");
        String redemptionPeriod = req.getParameter("redemptionPeriod");
        String startingAmountStr = req.getParameter("startingAmount");
        BigDecimal startingAmount = new BigDecimal(startingAmountStr);
        if (startingAmountStr != null && !"".equals(startingAmountStr)) {
            startingAmount = startingAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        String redemptionFeeStr = req.getParameter("redemptionFee");
        BigDecimal redemptionFee = new BigDecimal(redemptionFeeStr);
        if (redemptionFeeStr != null && !"".equals(redemptionFeeStr)) {
            redemptionFee = redemptionFee.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        String lockPeriod = req.getParameter("lockPeriod");
        ProductBasic basic = new ProductBasic();
        basic.setId(id);
        basic.setPname(pname);
        basic.setSeries(series);
        basic.setClassification(classification);
        basic.setMechanismName(mechanismName);
        basic.setOpenTime(openTime);
        basic.setCurrency(currency);
        basic.setStatus(status);
        basic.setYield(yield);
        basic.setReviewPerson(reviewPerson);
        basic.setCycle(cycle);
        basic.setManagementRate(managementRate);
        basic.setSubscriptionRate(subscriptionRate);
        basic.setAmount(amount);
        basic.setMethod(method);
        basic.setRedemptionPeriod(redemptionPeriod);
        basic.setStartingAmount(startingAmount);
        basic.setRedemptionFee(redemptionFee);
        basic.setLockPeriod(lockPeriod);
        Integer num = basicService.editBasicById(basic);
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

    public void getBasicName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Integer id = null;
        if (idStr != null && !"".equals(idStr)) {
            id = Integer.parseInt(idStr);
        }
        R r = new R();
        String basicName = basicService.getBasicName(id);
        if (basicName != null && !"".equals(basicName)) {
            r.setCode(ResponseEnum.REQ_SUCCESS.getCode());
            r.setMsg(ResponseEnum.REQ_SUCCESS.getMsg());
            r.setData(basicName);
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

    public void addManage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String basicIdStr = req.getParameter("basicId");
        Integer basicId = null;
        if (basicIdStr != null && !"".equals(basicIdStr)) {
            basicId = Integer.parseInt(basicIdStr);
        }
        String netWorth = req.getParameter("netWorth");
        String growthRate = req.getParameter("growthRate");
        String baseTime = req.getParameter("baseTime");
        ProductInCrease productInCrease = new ProductInCrease();
        productInCrease.setBasicId(basicId);
        productInCrease.setBaseTime(baseTime);
        productInCrease.setGrowthRate(growthRate);
        productInCrease.setNetWorth(netWorth);
        Integer num = basicService.addWorth(productInCrease);
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
}
