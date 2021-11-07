package com.yyq.meinian.service;

import com.yyq.meinian.pojo.User;

/**
 * @title: UserService
 * @Author yyq
 * @Date: 2021/11/5 17:24
 * @Version 1.0
 */
public interface UserService {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
