package com.yyq.meinian.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.entity.Result;
import com.yyq.meinian.pojo.Address;
import com.yyq.meinian.service.AddressService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Date:2021/11/4
 * Author:ybc
 * Description:
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;

    @RequestMapping("/findAllMaps")
    public List<Address> findAllMaps(){
        return addressService.findAllMaps();
    }

    @RequestMapping("/addAddress")
    public Result addAddress(@RequestBody Address address){
        addressService.addAddress(address);
        return new Result(true, "添加地址成功");
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return addressService.findPage(queryPageBean);
    }

}
