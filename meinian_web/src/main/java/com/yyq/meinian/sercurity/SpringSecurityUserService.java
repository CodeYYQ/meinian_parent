package com.yyq.meinian.sercurity;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyq.meinian.pojo.Permission;
import com.yyq.meinian.pojo.Role;
import com.yyq.meinian.pojo.User;
import com.yyq.meinian.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @title: SpringSecurityUserService
 * @Author yyq
 * @Date: 2021/11/5 17:21
 * @Version 1.0
 */
@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Reference
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据username查询用户信息
        User user = userService.getUserByUsername(username);
        //判断user是否为null
        if(user==null){
            //根据用户名未查询到用户信息
            return  null;
        }
        //根据用户名查询到用户信息
        //获取用户拥有的角色
        Set<Role> roles = user.getRoles();
        //对role循环，获取每个角色对应的权限
        Set<GrantedAuthority> set = new HashSet<>();
        for (Role role : roles) {
            //将用户所对应角色授权
            //set.add(new Simple}GrantedAuthority(role.getKeyword()));
            //获取每个角色权限
            Set<Permission> permissions = role.getPermissions();
            //获取每个角色所对应权限
            for (Permission permission : permissions) {
                set.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),set);
    }
}
