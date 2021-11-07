package com.yyq.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.constant.MessageConstant;
import com.yyq.meinian.constant.RedisMessageConstant;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.service.OrderService;
import com.yyq.meinian.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Map;

/**
 * @title: OrderController
 * @Author yyq
 * @Date: 2021/11/3 20:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    /**
     *orderInfo
     * name:旅游人姓名
     * sex:性别
     * telephone:电话
     * validateCode:验证码
     * orderDate:预约日期
     * setmealId:预约套餐游id
     * @return
     */
    @RequestMapping("/submitOrder")
    public Result submitOrder(@RequestBody Map map) throws Exception {
        //获取用户输入的验证码和手机号
        String validateCode = (String) map.get("validateCode");
        String telephone = (String) map.get("telephone");
        //获取redis中存储的正确验证码
        Jedis jedis = jedisPool.getResource();
        String codeInRedis = jedis.get(telephone + ":" + RedisMessageConstant.SENDTYPE_ORDER);
        //判读用户输入验证码是否正确
        if(codeInRedis != null && codeInRedis.equals(validateCode)){
            //验证码输入正确,通过service处理提交预约信息业务逻辑
            return orderService.submitOrder(map);


        }else{
            //验证输入错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

    }

    @RequestMapping("/findById")
    public Result findById(Integer id) throws Exception {
        Map map = orderService.findById(id);
        Date orderDate = (Date) map.get("orderDate");
        map.put("orderDate", DateUtils.parseDate2String(orderDate));
        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
    }
}
