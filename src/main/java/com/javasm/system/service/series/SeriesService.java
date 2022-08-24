package com.javasm.system.service.series;

import com.javasm.system.entity.Menu;
import com.javasm.system.entity.Series;

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
    List<Series> getSeries(Integer page,Integer pageSize);

    Integer addSeries(Series series);

    Long getAllSeriesNum();

}
