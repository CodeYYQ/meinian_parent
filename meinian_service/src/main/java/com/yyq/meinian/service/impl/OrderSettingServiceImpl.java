package com.yyq.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yyq.meinian.mapper.OrderSettingMapper;
import com.yyq.meinian.pojo.OrderSetting;
import com.yyq.meinian.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: OrderSettingService
 * @Author yyq
 * @Date: 2021/11/2 8:03
 * @Version 1.0
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void addOrderSettings(List<OrderSetting> orderSettingList) {
        for (OrderSetting orderSetting : orderSettingList) {
            //根据预约日期查询预约设置的记录数
            int count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
            //判断count范围
            if(count > 0){
                //修改预约日期所对应的可预约人数
                orderSettingMapper.updateOrderSetting(orderSetting);
            }else{
                //添加预约日期所对应的预约设置信息
                orderSettingMapper.addOrderSetting(orderSetting);
            }


        }
    }


    @Override
    public List getOrderSettingByMonth(String orderDate) {
        //根据日期获取预约设置信息
        String startData = orderDate + "-1";
        String endData = orderDate+"-31";
        List<OrderSetting> orderSettingList = orderSettingMapper.getOrderSettingByMonth(startData,endData);
        //将OrderSetting信息转换为页面所需要的格式
        List<Map> list = new ArrayList<>();
        for (OrderSetting orderSetting : orderSettingList) {
            Map map = new HashMap();
            map.put("date",orderSetting.getOrderDate().getDate());
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());
            list.add(map);
        }
        return list;
    }

    @Override
    public void updateNumberByOrderDate(OrderSetting orderSetting) {
        //根据预约日期查询预约设置的记录数
        int count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
        //判断count范围
        if(count > 0){
            //修改预约日期所对应的可预约人数
            orderSettingMapper.updateOrderSetting(orderSetting);
        }else{
            //添加预约日期所对应的预约设置信息
            orderSettingMapper.addOrderSetting(orderSetting);
        }
    }
}
