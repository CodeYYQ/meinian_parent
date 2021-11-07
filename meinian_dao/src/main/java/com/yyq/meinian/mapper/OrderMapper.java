package com.yyq.meinian.mapper;

import com.yyq.meinian.pojo.Order;
import com.yyq.meinian.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @title: OrderMapper
 * @Author yyq
 * @Date: 2021/11/3 20:29
 * @Version 1.0
 */
public interface OrderMapper {
    /**
     * 查询符合条件(同一个会员预约了同一天同一个套餐信息)的数据数量
     * @param order
     * @return
     */
    int findCountByCondition(Order order);

    /**
     * 更新已预约人数
     * @param orderSetting
     */
    void updateReservationsByOrderDate(OrderSetting orderSetting);

    /**
     * 添加预约信息
     * @param order
     */
    void addOrder(Order order);

    /**
     * 根据预约id查询预约相关信息
     * @param id
     * @return
     */
    Map findById(@Param("id") Integer id);

    /**
     * 获取今日预约数
     * @param orderDate
     * @return
     */
    int getTodayOrderNumber(String orderDate);

    /**
     * 获取今日出游数
     * @param orderDate
     * @return
     */
    int getTodayVisitsNumber(String orderDate);

    /**
     * 获取本周或本月预约数
     * @param startDate
     * @param endDate
     * @return
     */
    int getThisWeekOrMonthOrderNumber(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取本周或本月出游数
     * @param startDate
     * @param endDate
     * @return
     */
    int getThisWeekOrMonthVisitsNumber(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
