package com.yyq.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yyq.meinian.mapper.MemberMapper;
import com.yyq.meinian.pojo.Member;
import com.yyq.meinian.service.MemberService;
import com.yyq.meinian.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @title: MemberService
 * @Author yyq
 * @Date: 2021/11/5 7:16
 * @Version 1.0
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public void checkTelephone(Map map) {
        String telephone = (String) map.get("telephone");
        //根据手机号查询会员信息
        Member member =  memberMapper.findMemberByTelephone(telephone);
        //判断member是否为null
        if(member == null){
            //表示手机号未注册会员
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            memberMapper.registMember(member);
        }
    }

    @Override
    public List<Integer> getMemberCountByMonth(List<String> months) {
        List<Integer> memberCount = new ArrayList<>();
        for (String month : months) {
            //获取某个月最后一天
            String regTime = DateUtils.getLastDayOfMonth(month);
            //根据这个月最后一天查看该月注册会员数量
            Integer count = memberMapper.getMemberCountByMonthByRegTime(regTime);
            memberCount.add(count);
        }
        return memberCount;
    }
}
