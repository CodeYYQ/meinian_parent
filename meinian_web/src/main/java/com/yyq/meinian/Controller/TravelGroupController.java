package com.yyq.meinian.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.constant.MessageConstant;
import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.pojo.TravelGroup;
import com.yyq.meinian.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title: TravelGroupController
 * @Author yyq
 * @Date: 2021/10/29 15:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {
    @Reference
    private TravelGroupService travelGroupService;

    @RequestMapping("/add")
    public Result addTravelGroup(@RequestBody TravelGroup travelGroup,Integer[] travelItemIds){
        try {
            travelGroupService.addTravelGroup(travelGroup,travelItemIds);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return travelGroupService.findPage(queryPageBean);
    }

    @RequestMapping("/delete")
    public Result deleteTravelGroup(Integer id){
        try {
            travelGroupService.deleteTravelGroup(id);
            return new Result(true,MessageConstant.DELETE_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/getTravelItemByTravelGroupId")
    public Result getTravelItemByTravelGroupId(Integer id){
        try {
            List<Integer> list =  travelGroupService.getTravelItemByTravelGroupId(id);
            return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/update")
    public Result updateTravelGroup(@RequestBody TravelGroup travelGroup,Integer[] travelItemIds){
        try {
            travelGroupService.updateTravelGroup(travelGroup,travelItemIds);
            return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<TravelGroup> list = travelGroupService.findAll();
            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }
}
