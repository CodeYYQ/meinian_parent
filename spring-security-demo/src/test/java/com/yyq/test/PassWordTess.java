package com.yyq.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @title: PassWordTess
 * @Author yyq
 * @Date: 2021/11/5 15:11
 * @Version 1.0
 */
public class PassWordTess {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println("encode = " + encode);
    }
}
