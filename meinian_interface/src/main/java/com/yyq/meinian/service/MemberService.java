package com.yyq.meinian.service;

import java.util.List;
import java.util.Map;

/**
 * @title: MemberService
 * @Author yyq
 * @Date: 2021/11/5 7:16
 * @Version 1.0
 */
public interface MemberService {
    /**
     * 验证当前手机号是否已经注册会员，未注册，则注册
     * @param map
     */
    void checkTelephone(Map map);

    /**
     * 通过月份查询一年前到目前每月会员数量
     * @param months
     * @return
     */
    List<Integer> getMemberCountByMonth(List<String> months);
}
