package com.yyq.meinian.service;

import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @title: SetmealService
 * @Author yyq
 * @Date: 2021/11/1 11:57
 * @Version 1.0
 */
public interface SetmealService {
    /**
     * 添加套餐游信息
     * @param setmeal
     * @param travelGroupIds
     */
    void addSetmeal(Setmeal setmeal, Integer[] travelGroupIds);

    /**
     * 查询套餐游分页信息
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 查询所有套餐游信息
     * @return
     */
    List<Setmeal> findAllSetmeal();

    /**
     * 根据套餐id查询套餐信息
     * @param id
     * @return
     */
    Setmeal findById(Integer id);

    /**
     * 获取套餐预约占比信息
     * @return
     */
    List<Map> getSetmealReport();
}
