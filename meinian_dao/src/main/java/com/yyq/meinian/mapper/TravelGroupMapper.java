package com.yyq.meinian.mapper;

import com.github.pagehelper.Page;
import com.yyq.meinian.pojo.TravelGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @title: TravelGroupMapper
 * @Author yyq
 * @Date: 2021/10/29 15:41
 * @Version 1.0
 */
public interface TravelGroupMapper {
    /**
     * 添加跟团游信息
     * @param travelGroup
     */
    void addTravelGroup(TravelGroup travelGroup);

    /**
     * 添加跟团游跟自由行关系
     * @param travelGroupId
     * @param travelItemIds
     */
    void addTravelGroupAndTravelItem(@Param("travelGroupId") Integer travelGroupId,@Param("travelItemIds") Integer[] travelItemIds);

    /**
     * 根据条件查询跟团游信息
     * @param queryString
     * @return
     */
    Page<TravelGroup> findTravelGroupByQueryString(@Param("queryString") String queryString);

    /**
     * 删除跟团游信息
     * @param id
     */
    void deleteTravelGroup(@Param("id") Integer id);

    void deleteTravelGroupItem(@Param("travelGroupId")Integer travelGroupId);

    /**
     * 查询跟团游对应的自由行id
     * @param id
     * @return
     */
    List<Integer> getTravelItemByTravelGroupId(Integer id);

    /**
     * 修改跟团游信息
     * @param travelGroup
     */
    void updateTravelGroup(TravelGroup travelGroup);

    /**
     * 查询所有跟团游信息
     * @return
     */
    List<TravelGroup> findAll();

    /**
     * 分步查询第二步:根据套餐游查询跟团游信息
     * @param setmealId
     * @return
     */
    List<TravelGroup> findTravelGroupsBySetmealIdStep(@Param("setmealId")Integer setmealId);
}
