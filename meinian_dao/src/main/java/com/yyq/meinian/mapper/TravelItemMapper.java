package com.yyq.meinian.mapper;

import com.github.pagehelper.Page;
import com.yyq.meinian.pojo.TravelItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @title: TravelItemMapper
 * @Author yyq
 * @Date: 2021/10/28 11:00
 * @Version 1.0
 */
public interface TravelItemMapper {

    /**
     * 新增自由行
     * @param travelItem
     */
    void addTravelItem(TravelItem travelItem);

    /**
     * 根据查询条件查询自由行信息
     * @param queryString
     * @return
     */
    Page findTravelItemByQueryString(@Param("queryString") String queryString);

    /**
     * 删除自由行信息
     * @param id
     */
    void deleteTravelItem(Integer id);

    void updateTravelItem(TravelItem travelItem);

    /**
     * 查询所有自由行信息
     * @return
     */
    List<TravelItem> findAll();


    /**
     *分步查询三:根据跟团游id查询自由行信息
     * @param travelGroupId
     * @return
     */
    List<TravelItem> findTravelItemsByTravelGroupIdStep(@Param("travelGroupId") Integer travelGroupId);
}
