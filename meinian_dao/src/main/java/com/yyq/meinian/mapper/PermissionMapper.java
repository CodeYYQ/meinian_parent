package com.yyq.meinian.mapper;

import com.yyq.meinian.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @title: PromissionMapper
 * @Author yyq
 * @Date: 2021/11/5 17:28
 * @Version 1.0
 */
public interface PermissionMapper {

    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    Set<Permission> getPermissionByRoleId(@Param("roleId") Integer roleId);
}
