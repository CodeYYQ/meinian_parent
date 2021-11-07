package com.yyq.meinian.service;

import com.yyq.meinian.pojo.OrderSetting;

import java.util.List;

/**
 * @title: OrderSettingService
 * @Author yyq
 * @Date: 2021/11/2 8:04
 * @Version 1.0
 */
public interface OrderSettingService {

    /**
     * 批量导入预约信息
     * @param orderSettingList
     */
    void addOrderSettings(List<OrderSetting> orderSettingList);

    /**
     * 根据日期获取预约信息
     * @param orderDate
     * @return
     */
    List getOrderSettingByMonth(String orderDate);

    /**
     * 根据日期修改预约设置信息
     * @param orderSetting
     */
    void updateNumberByOrderDate(OrderSetting orderSetting);
}
