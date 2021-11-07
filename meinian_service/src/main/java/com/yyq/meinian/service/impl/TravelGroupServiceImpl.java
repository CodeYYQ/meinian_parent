package com.yyq.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.mapper.TravelGroupMapper;
import com.yyq.meinian.pojo.TravelGroup;
import com.yyq.meinian.service.TravelGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title: TravelGroupServiceImpl
 * @Author yyq
 * @Date: 2021/10/29 15:39
 * @Version 1.0
 */
@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {

    @Autowired
    private TravelGroupMapper travelGroupMapper;

    @Override
    public void addTravelGroup(TravelGroup travelGroup, Integer[] travelItemIds) {
        //添加跟团游信息
        travelGroupMapper.addTravelGroup(travelGroup);
        //添加跟团游跟自由行关系
        travelGroupMapper.addTravelGroupAndTravelItem(travelGroup.getId(),travelItemIds);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<TravelGroup> page = travelGroupMapper.findTravelGroupByQueryString(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteTravelGroup(Integer id) {
        deleteTravelGroupItem(id);
        travelGroupMapper.deleteTravelGroup(id);
    }

    @Override
    public void deleteTravelGroupItem(Integer travelGroupId) {
        travelGroupMapper.deleteTravelGroupItem(travelGroupId);
    }

    @Override
    public List<Integer> getTravelItemByTravelGroupId(Integer id) {
        return travelGroupMapper.getTravelItemByTravelGroupId(id);
    }

    @Override
    public void updateTravelGroup(TravelGroup travelGroup, Integer[] travelItemIds) {
        //修改跟团游信息
        travelGroupMapper.updateTravelGroup(travelGroup);
        //删除跟团游和自由行的中间表相关信息
        deleteTravelGroupItem(travelGroup.getId());
        //重新添加跟团游和自由的中间表相关信息
        travelGroupMapper.addTravelGroupAndTravelItem(travelGroup.getId(),travelItemIds);
    }

    @Override
    public List<TravelGroup> findAll() {
        return travelGroupMapper.findAll();
    }
}
