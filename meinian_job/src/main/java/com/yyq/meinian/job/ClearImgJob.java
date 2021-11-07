package com.yyq.meinian.job;

import com.yyq.meinian.constant.RedisConstant;
import com.yyq.meinian.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @title: ClearImgJob
 * @Author yyq
 * @Date: 2021/11/1 18:59
 * @Version 1.0
 */
@Component
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        //获取Jedis对象
        Jedis jedis = jedisPool.getResource();
        //获取redis中存储所有图片名称和存储数据库中图片名称差集
        Set<String> set = jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        //删除set集合中所有相对应的图片及七牛云
        for (String fileName : set) {
            jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            QiniuUtils.deleteFileFromQiniu(fileName);
            System.out.println("删除的图片 : " + fileName);
        }

    }
}
