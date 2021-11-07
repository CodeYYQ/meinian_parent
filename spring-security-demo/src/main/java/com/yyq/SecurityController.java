package com.yyq;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: SecurityController
 * @Author yyq
 * @Date: 2021/11/5 16:26
 * @Version 1.0
 */
@RestController
public class SecurityController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('delete')")
    public String sayHell(){
        return "hello,spring security";
    }
}
