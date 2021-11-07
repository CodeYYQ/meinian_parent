package com.yyq.meinian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.constant.MessageConstant;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.pojo.Setmeal;
import com.yyq.meinian.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title: SetmealController
 * @Author yyq
 * @Date: 2021/11/3 10:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/findAllSetmeal")
    public Result findAllSetmeal(){
        List<Setmeal> list = setmealService.findAllSetmeal();
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,list);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
       Setmeal setmeal =  setmealService.findById(id);
       return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }
}
