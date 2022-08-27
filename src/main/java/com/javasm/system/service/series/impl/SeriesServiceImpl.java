package com.javasm.system.service.series.impl;

import com.javasm.system.dao.series.impl.AreaDaoImpl;
import com.javasm.system.dao.series.impl.SeriesDaoImpl;
import com.javasm.system.entity.MyArea;
import com.javasm.system.entity.Series;
import com.javasm.system.entity.SeriesRemittance;
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
    public List<Series> getSeries(Integer page,Integer pageSize,Series querySeries) {
        return new SeriesDaoImpl().getSeries(page,pageSize,querySeries);
    }

    @Override
    public Integer addSeries(Series series) {
        return new SeriesDaoImpl().addSeries(series);
    }

    @Override
    public Long getAllSeriesNum(Series querySeries) {
        return new SeriesDaoImpl().getAllSeriesNum(querySeries);
    }

    @Override
    public Integer editSeries(SeriesRemittance editSeries) {
        return new SeriesDaoImpl().editSeries(editSeries);
    }

    @Override
    public SeriesRemittance getSeriesById(Integer sid) {
        return new SeriesDaoImpl().getSeriesById(sid);
    }

    @Override
    public Integer editForSeries(Series editforSeries) {
        return new SeriesDaoImpl().editForSeries(editforSeries);
    }

    @Override
    public List<MyArea> getAreaByParentId(Integer parentId) {
        return new AreaDaoImpl().getAreaByParentId(parentId);
    }


}
