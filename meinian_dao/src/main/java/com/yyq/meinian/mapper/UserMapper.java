package com.yyq.meinian.mapper;

import com.yyq.meinian.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @title: UserMapper
 * @Author yyq
 * @Date: 2021/11/5 17:25
 * @Version 1.0
 */
public interface UserMapper {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(@Param("username") String username);
}
