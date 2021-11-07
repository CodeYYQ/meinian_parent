package com.yyq.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yyq.meinian.mapper.UserMapper;
import com.yyq.meinian.pojo.User;
import com.yyq.meinian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @title: UserServiceImpl
 * @Author yyq
 * @Date: 2021/11/5 17:24
 * @Version 1.0
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
