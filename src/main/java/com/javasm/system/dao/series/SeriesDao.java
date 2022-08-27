package com.javasm.system.dao.series;

import com.javasm.system.entity.Menu;
import com.javasm.system.entity.Series;
import com.javasm.system.entity.SeriesRemittance;

import java.util.List;

/**
 * @author: LinHai
 * @className: SeriesDao
 * @Description:
 * @date: 2022/8/23 14:22
 * @version: 0.1
 * @since: jdk11
 */
public interface SeriesDao {
     List<Series> getSeries(Integer page,Integer pageSize,Series querySeries);

     Integer addSeries(Series series);

     Long getAllSeriesNum(Series querySeries);

     Integer editSeries(SeriesRemittance editSeries);

     SeriesRemittance getSeriesById(Integer sid);

     Integer editForSeries(Series editforSeries);





}
