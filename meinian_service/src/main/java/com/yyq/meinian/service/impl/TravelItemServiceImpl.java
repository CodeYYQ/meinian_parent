package com.yyq.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yyq.meinian.entity.PageResult;
import com.yyq.meinian.entity.QueryPageBean;
import com.yyq.meinian.mapper.TravelItemMapper;
import com.yyq.meinian.pojo.TravelItem;
import com.yyq.meinian.service.TravelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title: TravelItemServiceImpl
 * @Author yyq
 * @Date: 2021/10/28 10:58
 * @Version 1.0
 */
@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired
    private TravelItemMapper travelItemMapper;

    @Override
    public void addTravelItem(TravelItem travelItem) {
        travelItemMapper.addTravelItem(travelItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page page = travelItemMapper.findTravelItemByQueryString(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteTravelItem(Integer id) {
        travelItemMapper.deleteTravelItem(id);
    }

    @Override
    public void updateTravelItem(TravelItem travelItem) {
        travelItemMapper.updateTravelItem(travelItem);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemMapper.findAll();
    }
}
