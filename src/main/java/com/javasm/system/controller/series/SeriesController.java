package com.javasm.system.controller.series;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.javasm.system.controller.BaseController;
import com.javasm.system.dao.series.impl.SeriesDaoImpl;
import com.javasm.system.entity.*;
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

    //获取修改页面数据
    public void editMenuSeries(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sidStr = req.getParameter("sid");
        String sname = req.getParameter("sname");
        String sename = req.getParameter("sename");
        String remittance = req.getParameter("remittance");

        Integer sid = null;
        if (sidStr != null && !"".equals(sidStr)) {
            sid = Integer.parseInt(sidStr);
        }

        Series editseries = new Series(sid, sname, sename, remittance);
        SeriesServiceImpl seriesService = new SeriesServiceImpl();
        Integer integer = seriesService.editForSeries(editseries);

        R r = new R();
        if (integer > 0) {
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

        //获取指定id所有的所有信息
        public void getSeriesForEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sidStr = req.getParameter("sid");
        Integer sid = null;
        if(sidStr!=null&&!"".equals(sidStr)){
            sid = Integer.parseInt(sidStr);
        }
        SeriesServiceImpl ms = new SeriesServiceImpl();
        SeriesRemittance seriesById = ms.getSeriesById(sid);

        R r = new R();
        if(seriesById!=null){
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(seriesById);
        }else{
            r.setCode(ResponseEnum.NO_DATA.getCode());
            r.setMsg(ResponseEnum.NO_DATA.getMsg());
        }

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSONString(r));
        writer.flush();
        writer.close();
    }

//c查询 分页查询
    public void getSeriesSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer page = 1;
        Integer pageSize = 10;

        String pageStr = req.getParameter("page");
        String pageSizeStr = req.getParameter("pageSize");
        //模糊查询
        String sname = req.getParameter("sname");

        Series seriesSname = new Series(sname);

        //如果传了 使用传的值 如果没传 使用默认值
        if(pageStr!=null&&!"".equals(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(pageSizeStr!=null&&!"".equals(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }


        SeriesServiceImpl seriesService = new SeriesServiceImpl();
        List<Series> series = seriesService.getSeries(page,pageSize,seriesSname);



        //查询总记录数
        Long allSeriesNum = seriesService.getAllSeriesNum(seriesSname);

        R r = new R();
        if(series.size()>0){
            r.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            r.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            r.setData(series);

//            System.out.println(series);

            System.out.println(series);

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
    public void addEditRemittance(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        private String bankName;
//        private String bankSwift;
//        private String bankCode;
//        private String cnapsNumber;
//        private String bankArea;
//        private String bankCity;
//        private String payeeName;
//        private String payeeAccount;
//        private String payeeAddress;
//        private String InformationOrganization;
//        private Integer sid;
        String sidStr = req.getParameter("sid");
        String sname = req.getParameter("sname");
        String sename = req.getParameter("sename");
        String remittance = req.getParameter("remittance");
        String bankName = req.getParameter("bankName");
        String bankSwift = req.getParameter("bankSwift");
        String bankCode = req.getParameter("bankCode");
        String cnapsNumber = req.getParameter("cnapsNumber");
        String bankArea = req.getParameter("bankArea");
        String bankCity = req.getParameter("bankCity");
        String payeeName = req.getParameter("payeeName");
        String payeeAccount = req.getParameter("payeeAccount");
        String payeeAddress = req.getParameter("payeeAddress");
        String InformationOrganization = req.getParameter("InformationOrganization");


        Integer sid = null;
        if (sidStr != null && !"".equals(sidStr)) {
            sid = Integer.parseInt(sidStr);
        }

        SeriesRemittance seriesRemittance = new SeriesRemittance(sid,sname,sename,remittance,bankName,bankSwift,bankCode,cnapsNumber,bankArea,bankCity,
                payeeName,payeeAccount,payeeAddress,InformationOrganization);

        SeriesServiceImpl seriesService = new SeriesServiceImpl();
        Integer integer = seriesService.editSeries(seriesRemittance);

//        Integer num = seriesService.addSeries(new Series(sid, sname,sename, remittance));
        R r = new R();
        if (integer > 0) {
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

        System.out.println("执行汇款信息添加修改");

    }

    //省市级联
    public void getAreaById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parentIdStr = req.getParameter("parentId");
        Integer parentId = null ;
        if (parentIdStr!=null&&!"".equals(parentIdStr)){
            parentId = Integer.parseInt(parentIdStr);
        }
        /*
         * 2.调用业务逻辑层代码
         * */
        SeriesServiceImpl seriesService = new SeriesServiceImpl();
        List<MyArea> areaList= seriesService.getAreaByParentId(parentId);
        /*
         * 根据执行结果 返回不同的数据
         *
         * */
        R re = new R();
        if(areaList.size()>0){
            re.setCode(ResponseEnum.QUERY_SUCCESS.getCode());
            re.setMsg(ResponseEnum.QUERY_SUCCESS.getMsg());
            re.setData(areaList);
        }else{
            re.setCode(ResponseEnum.NO_DATA.getCode());
            re.setMsg(ResponseEnum.NO_DATA.getMsg());
        }

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSONString(re));
        writer.flush();
        writer.close();



    }


    }
