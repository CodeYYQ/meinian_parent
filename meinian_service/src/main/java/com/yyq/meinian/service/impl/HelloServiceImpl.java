package com.yyq.meinian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yyq.meinian.service.HelloService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @title: HelloServiceImpl
 * @Author yyq
 * @Date: 2021/10/27 19:05
 * @Version 1.0
 */
@Service(interfaceClass = HelloService.class)
@Transactional
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello,"+name;
    }
}
