package com.yyq.meinian.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.constant.MessageConstant;
import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.pojo.TravelItem;
import com.yyq.meinian.service.TravelItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @title: TraverlItemController
 * @Author yyq
 * @Date: 2021/10/28 10:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference
    private TravelItemService travelItemService;

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('TRAVELITEM_ADD')")
    public Result addTravelItem(@RequestBody TravelItem travelItem){
        try {
            travelItemService.addTravelItem(travelItem);
            return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/findPage")
    @PreAuthorize("hasAuthority('TRAVELITEM_QUERY')")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return travelItemService.findPage(queryPageBean);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('TRAVELITEM_DELETE1')")
    public Result deleteTravelItem(Integer id){
        try {
            travelItemService.deleteTravelItem(id);
            return new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/update")
    @PreAuthorize("hasAuthority('TRAVELITEM_EDIT')")
    public Result updateTravelItem(@RequestBody TravelItem travelItem){
        try {
            travelItemService.updateTravelItem(travelItem);
            return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/findAll")
    @PreAuthorize("hasAuthority('TRAVELITEM_QUERY')")
    public Result findAll(){
        try {
            List<TravelItem> list = travelItemService.findAll();
            return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,list);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

}
