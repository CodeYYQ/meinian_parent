package com.yyq.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yyq.meinian.constant.RedisConstant;
import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.mapper.SetmealMapper;
import com.yyq.meinian.pojo.Setmeal;
import com.yyq.meinian.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * @title: SetmealServiceImpl
 * @Author yyq
 * @Date: 2021/11/1 11:58
 * @Version 1.0
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void addSetmeal(Setmeal setmeal, Integer[] travelGroupIds) {
        //添加套餐游信息
        setmealMapper.addSetmeal(setmeal);
        //添加套餐游与跟团游中间表信息
        setmealMapper.addSetmealAndTravelGroup(setmeal.getId(),travelGroupIds);
        //将保存到数据库中的图片名称保存到redis中
        Jedis jedis = jedisPool.getResource();
        jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Setmeal> page = setmealMapper.findSetmealByQueryString(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Setmeal> findAllSetmeal() {
        return setmealMapper.findAllSetmeal();
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.findSetmealByIdStep(id);
    }

    @Override
    public List<Map> getSetmealReport() {
        return setmealMapper.getSetmealReport();
    }
}
