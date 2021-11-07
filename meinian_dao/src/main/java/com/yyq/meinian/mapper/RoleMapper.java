package com.yyq.meinian.mapper;

import com.yyq.meinian.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @title: RoleMapper
 * @Author yyq
 * @Date: 2021/11/5 17:27
 * @Version 1.0
 */
public interface RoleMapper {

    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    Set<Role> getRolesByUserId(@Param("userId") Integer userId);
}
