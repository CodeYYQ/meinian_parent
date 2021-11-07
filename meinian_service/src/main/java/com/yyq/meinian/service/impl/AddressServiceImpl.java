package com.yyq.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.mapper.AddressMapper;
import com.yyq.meinian.pojo.Address;
import com.yyq.meinian.service.AddressService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Date:2021/11/4
 * Author:ybc
 * Description:
 */
@Service(interfaceClass = AddressService.class)
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> findAllMaps() {
        return addressMapper.findAllMaps();
    }

    @Override
    public void addAddress(Address address) {
        addressMapper.addAddress(address);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Address> page = addressMapper.getAddressByQueryString(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }
}
