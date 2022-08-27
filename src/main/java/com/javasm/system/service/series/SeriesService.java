package com.javasm.system.service.series;

import com.javasm.system.entity.Menu;
import com.javasm.system.entity.MyArea;
import com.javasm.system.entity.Series;
import com.javasm.system.entity.SeriesRemittance;

import java.util.List;

/**
 * @author: LinHai
 * @className: SeriesService
 * @Description:
 * @date: 2022/8/23 14:55
 * @version: 0.1
 * @since: jdk11
 */
public interface SeriesService {
    List<Series> getSeries(Integer page,Integer pageSize,Series querySeries);

    Integer addSeries(Series series);

    Long getAllSeriesNum(Series querySeries);

    Integer editSeries(SeriesRemittance editSeries);

    SeriesRemittance getSeriesById(Integer sid);

    Integer editForSeries(Series editforSeries);
    //省市级联
    List<MyArea> getAreaByParentId(Integer parentId);

}
