package com.javasm.system.service.series.impl;

import com.javasm.system.dao.series.impl.SeriesDaoImpl;
import com.javasm.system.entity.Series;
import com.javasm.system.service.series.SeriesService;

import java.util.List;

/**
 * @author: LinHai
 * @className: SeriesServiceImpl
 * @Description:
 * @date: 2022/8/23 14:56
 * @version: 0.1
 * @since: jdk11
 */
public class SeriesServiceImpl implements SeriesService {


    @Override
    public List<Series> getSeries(Integer page,Integer pageSize) {
        return new SeriesDaoImpl().getSeries(page,pageSize);
    }

    @Override
    public Integer addSeries(Series series) {
        return new SeriesDaoImpl().addSeries(series);
    }

    @Override
    public Long getAllSeriesNum() {
        return new SeriesDaoImpl().getAllSeriesNum();
    }


}
