package com.yyq.meinian.service;

import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.pojo.TravelItem;

import java.util.List;

/**
 * @title: TravelItemService
 * @Author yyq
 * @Date: 2021/10/28 10:57
 * @Version 1.0
 */
public interface TravelItemService {

    /**
     * 新增自由行
     * @param travelItem
     */
    void addTravelItem(TravelItem travelItem);

    /**
     * 查询分页数据
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 删除自由行信息
     * @param id
     */
    void deleteTravelItem(Integer id);

    /**
     * 修改自由行系信息
     * @param travelItem
     */
    void updateTravelItem(TravelItem travelItem);

    /**
     * 查询所有自由行信息
     * @return
     */
    List<TravelItem> findAll();
}
