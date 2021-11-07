package com.yyq.meinian.service;

import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.pojo.TravelGroup;

import java.util.List;

/**
 * @title: TravelGroupService
 * @Author yyq
 * @Date: 2021/10/29 15:39
 * @Version 1.0
 */
public interface TravelGroupService {

    /**
     * 添加更团游信息
     * @param travelGroup
     * @param travelItemIds
     */
    void addTravelGroup(TravelGroup travelGroup, Integer[] travelItemIds);

    /**
     * 查询跟团游分页信息
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 删除跟团游信息
     * @param id
     */
    void deleteTravelGroup(Integer id);

    /**
     * 删除跟团游和自由行中间表
     * @param travelGroupId :跟团游id
     */
    void deleteTravelGroupItem(Integer travelGroupId);

    /**
     * 查询跟团游对应的自由行id
     * @param id
     * @return
     */
    List<Integer> getTravelItemByTravelGroupId(Integer id);

    /**
     * 编辑跟团游信息
     * @param travelGroup
     * @param travelItemIds
     */
    void updateTravelGroup(TravelGroup travelGroup, Integer[] travelItemIds);

    /**
     * 查询所有跟团游信息
     * @return
     */
    List<TravelGroup> findAll();
}
