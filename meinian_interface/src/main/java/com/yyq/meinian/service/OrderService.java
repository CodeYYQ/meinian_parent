package com.yyq.meinian.service;

import com.yyq.meinian.entity.Result;

import java.util.Map;

/**
 * @title: OrderService
 * @Author yyq
 * @Date: 2021/11/3 20:27
 * @Version 1.0
 */
public interface OrderService {
    /**
     * 提交预约信息
     * @param map
     * @return
     */
    Result submitOrder(Map map) throws Exception;

    /**
     * 根据预约id查询预约相关信息
     * @param id
     * @return
     */
    Map findById(Integer id);
}
