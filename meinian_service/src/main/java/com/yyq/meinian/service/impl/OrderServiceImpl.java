package com.yyq.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yyq.meinian.constant.MessageConstant;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.mapper.MemberMapper;
import com.yyq.meinian.mapper.OrderMapper;
import com.yyq.meinian.mapper.OrderSettingMapper;
import com.yyq.meinian.pojo.Member;
import com.yyq.meinian.pojo.Order;
import com.yyq.meinian.pojo.OrderSetting;
import com.yyq.meinian.service.OrderService;
import com.yyq.meinian.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @title: OrderServiceImpl
 * @Author yyq
 * @Date: 2021/11/3 20:28
 * @Version 1.0
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Autowired
    private MemberMapper memberMapper;


    /**
     *orderInfo
     * name:旅游人姓名
     * sex:性别
     * telephone:电话
     * validateCode:验证码
     * orderDate:预约日期
     * setmealId:预约套餐游id
     *
     */
    @Override
    public Result submitOrder(Map map) throws Exception {
        //获取用户选择的预约信息
        String orderDate = (String) map.get("orderDate");
        //根据预约日期查询预约设置信息
        OrderSetting orderSetting = orderSettingMapper.findOrderSettingByOrderDate(orderDate);
        //判断orderSetting是否位null
        if(orderSetting == null){
            //表示当前的预约信息不能预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }else {
            //表示当前日期可以预约,判断预约人数是否已满
            if (orderSetting.getNumber() == orderSetting.getReservations()) {
                //预约已满
                return new Result(false, MessageConstant.ORDER_FULL);
            }
        }
            //获取用户手机号
            String telephone = (String) map.get("telephone");
            //通过手机号查询会员信息
            Member member = memberMapper.findMemberByTelephone(telephone);
            //判断member是否为null
            if(member == null){
                //表示手机号没有注册为会员,注册会员
                member = new Member();
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());
                member.setIdCard((String) map.get("idCard"));
                member.setSex((String) map.get("sex"));
                member.setSex((String) map.get("name"));
                memberMapper.registMember(member);
            }else{
                //表示手机号已经注册为会员,判断当前会员是否已经预约同一个套餐信息系
                Order order = new Order();
                order.setMemberId(member.getId());
                order.setOrderDate(DateUtils.parseString2Date(orderDate));
                order.setSetmealId(Integer.valueOf((String) map.get("setmealId")));
                int count = orderMapper.findCountByCondition(order);
                if(count > 0){
                    //表示当前会员已经预约过了
                    return new Result(false,MessageConstant.HAS_ORDERED);
                }
            }
        //表示可以完成预约
        //将预约日期所对应预约设置信息的已预约人数+1
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderMapper.updateReservationsByOrderDate(orderSetting);
        //添加预约信息
        Order order = new Order();
        order.setSetmealId(Integer.valueOf((String) map.get("setmealId")));
        order.setOrderDate(DateUtils.parseString2Date(orderDate));
        order.setMemberId(member.getId());
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setOrderType(Order.ORDERTYPE_WEIXIN);
        orderMapper.addOrder(order);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());

    }

    @Override
    public Map findById(Integer id) {
        return orderMapper.findById(id);
    }
}
