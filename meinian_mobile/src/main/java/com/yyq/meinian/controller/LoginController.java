package com.yyq.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.constant.MessageConstant;
import com.yyq.meinian.constant.RedisMessageConstant;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @title: LoginController
 * @Author yyq
 * @Date: 2021/11/5 7:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private MemberService memberService;

    @RequestMapping("/checkLogin")
    public Result checkLogin(@RequestBody Map map){
        //获取用户输入的手机号和验证码
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        //获取redis中存储正确的验证码
        Jedis jedis = jedisPool.getResource();
        String codeInRedis = jedis.get(telephone + ":" + RedisMessageConstant.SENDTYPE_LOGIN);
        //判断用户输入验证码是否正确
        if(codeInRedis !=null && codeInRedis.equals(validateCode)){
            //验证码正确,判断当前手机号是否已注册会员
            memberService.checkTelephone(map);
            return new Result(true, MessageConstant.LOGIN_SUCCESS);
        }else{
            //验证码错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }

}
