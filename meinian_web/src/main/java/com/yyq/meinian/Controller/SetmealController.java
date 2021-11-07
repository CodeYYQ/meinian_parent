package com.yyq.meinian.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.constant.MessageConstant;
import com.yyq.meinian.constant.RedisConstant;
import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.pojo.Setmeal;
import com.yyq.meinian.service.SetmealService;
import com.yyq.meinian.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * @title: StemealController
 * @Author yyq
 * @Date: 2021/11/1 11:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/upload")
    public Result uploadFile(MultipartFile imgFile){
        try {
            //获取上传文件文件名
            String filename = imgFile.getOriginalFilename();
            //获取文件后缀名
            String hzName = filename.substring(filename.lastIndexOf("."));
            //使用UUID作为文件名
            String uuid = UUID.randomUUID().toString();
            filename = uuid + hzName;
            //上传文件功能
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),filename);
            //将上传成功的文件名称存储到redis中
            Jedis jedis = jedisPool.getResource();
            jedis.sadd(RedisConstant.SETMEAL_PIC_RESOURCES,filename);
            //上传成功
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,filename);
        } catch (IOException e) {
            //上传失败
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
    @RequestMapping("/add")
    public Result addSetmeal(@RequestBody Setmeal setmeal,Integer[] travelGroupIds){
        try {
            setmealService.addSetmeal(setmeal,travelGroupIds);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
            return setmealService.findPage(queryPageBean);
    }
}
