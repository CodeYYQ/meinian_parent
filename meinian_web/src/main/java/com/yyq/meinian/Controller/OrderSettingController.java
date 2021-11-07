package com.yyq.meinian.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.constant.MessageConstant;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.pojo.OrderSetting;
import com.yyq.meinian.service.OrderSettingService;
import com.yyq.meinian.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @title: OrderSettingController
 * @Author yyq
 * @Date: 2021/11/2 8:01
 * @Version 1.0
 */
@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result uploadOrderSetting(MultipartFile excelFile) {
        //通过POIUtils读取excel文件中的内容
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);
            //将list集合中每个数组数据转换为OrderSetting
            List<OrderSetting> orderSettingList = new ArrayList<>();
            for (String[] strings : list) {
                OrderSetting orderSetting = new OrderSetting(new Date(strings[0]),Integer.parseInt(strings[1]));
                orderSettingList.add(orderSetting);
            }
            //批量导入预约信息
            orderSettingService.addOrderSettings(orderSettingList);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String orderDate){
        try {
            List list = orderSettingService.getOrderSettingByMonth(orderDate);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/updateNumberByOrderDate")
    public Result updateNumberByOrderDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.updateNumberByOrderDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
