package com.yyq.meinian.mapper;

import com.yyq.meinian.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @title: OrderSettingMapper
 * @Author yyq
 * @Date: 2021/11/2 8:05
 * @Version 1.0
 */
public interface OrderSettingMapper {


    /**
     * 根据预约日期查询预约设置的记录数
     * @param orderDate
     * @return
     */
    int findCountByOrderDate(@Param("orderDate") Date orderDate);

    /**
     * 修改预约日期所对应的可预约人数
     * @param orderSetting
     */
    void updateOrderSetting(OrderSetting orderSetting);

    /**
     * 添加预约日期所对应的预约设置信息
     * @param orderSetting
     */
    void addOrderSetting(OrderSetting orderSetting);

    /**
     * 根据日期查询预约设置信息
     * @param startData
     * @param endData
     * @return
     */
    List<OrderSetting> getOrderSettingByMonth(@Param("startData") String startData,@Param("endData") String endData);

    /**
     * 根据预约日期查询预约设置信息
     * @param orderDate
     * @return
     */
    OrderSetting findOrderSettingByOrderDate(@Param("orderDate") String orderDate);
}
