package com.yyq.meinian.mapper;

import com.github.pagehelper.Page;
import com.yyq.meinian.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @title: SetmealMapper
 * @Author yyq
 * @Date: 2021/11/1 12:00
 * @Version 1.0
 */
public interface SetmealMapper {
    /**
     * 添加套餐游信息
     * @param setmeal
     */
    public void addSetmeal(Setmeal setmeal);

    /**
     * 添加套餐游与跟团游中间表信息
     * @param id
     * @param travelGroupIds
     */
    void addSetmealAndTravelGroup(@Param("id") Integer id,@Param("travelGroupIds") Integer[] travelGroupIds);

    /**
     * 根据条件查询套餐游信息
     * @param queryString
     * @return
     */
    Page<Setmeal> findSetmealByQueryString(@Param("queryString") String queryString);

    /**
     * 查询所有套餐游信息
     * @return
     */
    List<Setmeal> findAllSetmeal();


    /**
     * 分步查询套餐游、套餐游所对应的跟团游、跟团游所对应的自由行信息
     * @param id
     * @return
     */
    Setmeal findSetmealByIdStep(@Param("id") Integer id);

    /**
     * 获取套餐预约占比信息
     * @return
     */
    List<Map> getSetmealReport();

    /**
     * 获取热门套餐
     * @return
     */
    List<Map> getHotSetmeal();
}
