package com.javasm.system.dao.series.impl;

import com.javasm.system.dao.series.AreaDao;
import com.javasm.system.entity.MyArea;

import com.javasm.system.util.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: LinHai
 * @className: AreaDaoImpl
 * @Description:
 * @date: 2022/8/26 10:40
 * @version: 0.1
 * @since: jdk11
 */
public class AreaDaoImpl implements AreaDao {
    @Override
    public List<MyArea> getAreaByParentId(Integer parentId) {
        Connection conn = DruidUtils.getConnection();
        String sql = "select * from tb_area ta where ta.parent_id = ?";
        List<MyArea> areaList = new ArrayList<MyArea>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,parentId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                /*
                 * 封装结果集
                 * 程序员手动写
                 * ORM映射工具  实体关系映射
                 * */

                int areaId = resultSet.getInt("area_id");
                String areaName = resultSet.getString("area_name");
                MyArea area = new MyArea(areaId,areaName);
                areaList.add(area);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtils.close(conn);
        }

        return areaList;
    }
}
