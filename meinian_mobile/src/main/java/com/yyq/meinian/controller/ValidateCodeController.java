package com.yyq.meinian.controller;

import com.yyq.meinian.constant.MessageConstant;
import com.yyq.meinian.constant.RedisMessageConstant;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.utils.SMSUtils;
import com.yyq.meinian.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @title: validateCodeController
 * @Author yyq
 * @Date: 2021/11/3 19:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;


    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){

        try {
            //获取验证码
            String validateCode = ValidateCodeUtils.generateValidateCode4String(6);
            //发送验证码
            SMSUtils.sendShortMessage(telephone,validateCode);
            //将验证码保存在redis中
            Jedis jedis = jedisPool.getResource();
            jedis.setex(telephone+":"+ RedisMessageConstant.SENDTYPE_ORDER,300,validateCode);
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        try {
            //获取验证码
            String validateCode = ValidateCodeUtils.generateValidateCode4String(6);
            //发送验证码
            SMSUtils.sendShortMessage(telephone,validateCode);
            //将验证码保存在redis中
            Jedis jedis = jedisPool.getResource();
            jedis.setex(telephone+":"+ RedisMessageConstant.SENDTYPE_LOGIN,300,validateCode);
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }
}
