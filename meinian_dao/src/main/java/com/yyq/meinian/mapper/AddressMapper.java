package com.yyq.meinian.mapper;

import com.yyq.meinian.pojo.Address;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Date:2021/11/4
 * Author:ybc
 * Description:
 */
public interface AddressMapper {

    /**
     * 查询分公司地址信息
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
     * @param queryString
     * @return
     */
    Page<Address> getAddressByQueryString(@Param("queryString") String queryString);
}
