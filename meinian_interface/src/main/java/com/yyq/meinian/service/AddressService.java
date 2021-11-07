package com.yyq.meinian.service;

import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.pojo.Address;

import java.util.List;

/**
 * Date:2021/11/4
 * Author:ybc
 * Description:
 */
public interface AddressService {

    /**
     * 查询所有的分公司地址信息
     * @return
     */
    List<Address> findAllMaps();

    /**
     * 添加分公司地址
     * @param address
     */
    void addAddress(Address address);

    /**
     * 查询分公司地址的分页信息
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);
}
