package com.yyq.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yyq.meinian.mapper.MemberMapper;
import com.yyq.meinian.mapper.OrderMapper;
import com.yyq.meinian.mapper.SetmealMapper;
import com.yyq.meinian.service.ReportService;
import com.yyq.meinian.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date:2021/11/4
 * Author:ybc
 * Description:
 */
@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * reportDate: 日期
     * todayNewMember: 新增会员数
     * totalMember: 总会员数
     * thisWeekNewMember: 本周新增会员数
     * thisMonthNewMember: 本月新增会员数
     * todayOrderNumber: 今日预约数
     * todayVisitsNumber: 今日出游数
     * thisWeekOrderNumber: 本周预约数
     * thisWeekVisitsNumber: 本周出游数
     * thisMonthOrderNumber: 本月预约数
     * thisMonthVisitsNumber: 本月出游数
     * hotSetmeal: 热门套餐
     * @return
     */

    @Override
    public Map getBusinessReportData() {
        Map map = new HashMap();
        try {
            //获取统计日期
            String reportDate = DateUtils.parseDate2String(new Date());
            //获取本周周一的日期
            String weekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
            //获取本周周日的日期
            String weekSunday = DateUtils.parseDate2String(DateUtils.getSundayOfThisWeek());
            //获取本月第一天的日期
            String monthFirst = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
            //获取本月最后一天的日期
            String monthLast = DateUtils.parseDate2String(DateUtils.getLastDay4ThisMonth());
            //获取新增会员数
            int todayNewMember = memberMapper.getTodayNewMember(reportDate);
            //获取总会员数
            int totalMember = memberMapper.getTotalMember();
            //获取本周新增会员数
            int thisWeekNewMember = memberMapper.getThisWeekOrMonthNewMember(weekMonday);
            //获取本月新增会员数
            int thisMonthNewMember = memberMapper.getThisWeekOrMonthNewMember(monthFirst);
            //获取今日预约数
            int todayOrderNumber = orderMapper.getTodayOrderNumber(reportDate);
            //获取今日出游数
            int todayVisitsNumber = orderMapper.getTodayVisitsNumber(reportDate);
            //获取本周预约数
            int thisWeekOrderNumber = orderMapper.getThisWeekOrMonthOrderNumber(weekMonday, weekSunday);
            //获取本周出游数
            int thisWeekVisitsNumber = orderMapper.getThisWeekOrMonthVisitsNumber(weekMonday, weekSunday);
            //获取本月预约数
            int thisMonthOrderNumber = orderMapper.getThisWeekOrMonthOrderNumber(monthFirst, monthLast);
            //获取本月出游数
            int thisMonthVisitsNumber = orderMapper.getThisWeekOrMonthVisitsNumber(monthFirst, monthLast);
            //获取热门套餐
            List<Map> hotSetmeal = setmealMapper.getHotSetmeal();
            map.put("reportDate", reportDate);
            map.put("todayNewMember", todayNewMember);
            map.put("totalMember", totalMember);
            map.put("thisWeekNewMember", thisWeekNewMember);
            map.put("thisMonthNewMember", thisMonthNewMember);
            map.put("todayOrderNumber", todayOrderNumber);
            map.put("todayVisitsNumber", todayVisitsNumber);
            map.put("thisWeekOrderNumber", thisWeekOrderNumber);
            map.put("thisWeekVisitsNumber", thisWeekVisitsNumber);
            map.put("thisMonthOrderNumber", thisMonthOrderNumber);
            map.put("thisMonthVisitsNumber", thisMonthVisitsNumber);
            map.put("hotSetmeal", hotSetmeal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
