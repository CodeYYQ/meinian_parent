package com.yyq.service;


import com.yyq.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @title: UserService
 * @Author yyq
 * @Date: 2021/11/5 14:13
 * @Version 1.0
 */
@Component
public class UserService implements UserDetailsService {

    private static Map<String, User> map = new HashMap<>();

    static {
        map.put("yyq",new User(1001,"yyq","$2a$10$T2wBHp1TKq4oovKblm8b5uEadOX3jx2fQKJWNwrijFBlMBefkrxH6"));
        map.put("IU",new User(1002,"IU","$2a$10$T2wBHp1TKq4oovKblm8b5uEadOX3jx2fQKJWNwrijFBlMBefkrxH6"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        User user = map.get(username);
        //判断user是否为null
        if(user == null){
            //表示用户名不存在
            return  null;
        }
        //表示用户名存在,则授权
        Set<GrantedAuthority>  authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("add"));
        authorities.add(new SimpleGrantedAuthority("delete"));
        authorities.add(new SimpleGrantedAuthority("query"));
        //return new org.springframework.security.core.userdetails.User(username, "{noop}"+user.getPassword(), authorities);
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }


}
