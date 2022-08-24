package com.javasm.system.controller;

import com.google.gson.Gson;
import com.javasm.system.entity.result.R;
import com.javasm.system.entity.result.ResponseEnum;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/users/upload")
@MultipartConfig
public class UpLoadController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("myfile");
        String basePath = req.getServletContext().getRealPath("/");
        String folderPath = "upload/";
        String fileName = part.getSubmittedFileName();
        String returnPath = folderPath + fileName;
        String servePath="http://localhost:8080/";
        part.write(basePath + folderPath+fileName);
        resp.setContentType("application/json;charset=utf-8");
        R r = new R();
        r.setCode(ResponseEnum.UPLOAD_SUCCESS.getCode());
        r.setMsg(ResponseEnum.UPLOAD_SUCCESS.getMsg());
        r.setData(servePath+returnPath);
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(r));
        writer.flush();
        writer.close();
    }
}
