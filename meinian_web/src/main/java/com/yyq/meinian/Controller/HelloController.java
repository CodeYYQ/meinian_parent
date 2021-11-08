package com.yyq.meinian.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: HelloController
 * @Author yyq
 * @Date: 2021/10/27 20:00
 * @Version 1.0
 */
@RestController
public class HelloController {
    @Reference
    private HelloService helloService;

    @RequestMapping(value = "/hello")
    public String sayHello(String name){
        System.out.println("name = " + name);
        System.out.println("hot-fix");
        System.out.println("master");
        System.out.println("host-fix");
        System.out.println("hhhh");
        System.out.println("hhhh2");
        System.out.println("hhhh2");
        System.out.println("垃圾");
        return helloService.sayHello(name);
    }


}
