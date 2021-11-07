package com.yyq.meinian.mapper;

import com.yyq.meinian.pojo.Member;
import org.apache.ibatis.annotations.Param;

/**
 * @title: MemberMapper
 * @Author yyq
 * @Date: 2021/11/3 20:54
 * @Version 1.0
 */
public interface MemberMapper {

    /**
     * 根据手机号查询会员信息
     * @param telephone
     * @return
     */
    Member findMemberByTelephone(@Param("telephone") String telephone);

    /**
     * 注册会员信息
     * @param member
     */
    void registMember(Member member);

    /**
     * 根据这个月最后一天查看该月注册会员数量
     * @param regTime
     * @return
     */
    Integer getMemberCountByMonthByRegTime(String regTime);


    /**
     * 获取新增会员数
     * @param regTime
     * @return
     */
    int getTodayNewMember(@Param("regTime") String regTime);

    /**
     * 获取总会员数
     * @return
     */
    int getTotalMember();

    /**
     * 获取本周或本月新增会员数
     * @param regTime
     * @return
     */
    int getThisWeekOrMonthNewMember(@Param("regTime") String regTime);

}
