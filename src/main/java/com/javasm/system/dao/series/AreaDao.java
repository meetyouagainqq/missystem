package com.javasm.system.dao.series;

import com.javasm.system.entity.MyArea;

import java.util.List;

/**
 * @author: LinHai
 * @className: AreaDao
 * @Description:
 * @date: 2022/8/26 10:38
 * @version: 0.1
 * @since: jdk11
 */
public interface AreaDao {

    List<MyArea> getAreaByParentId(Integer parentId);

}
