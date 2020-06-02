package com.cn.hnust.dao;
import java.util.List;

import com.cn.hnust.model.Permission;
import com.cn.hnust.model.Role;

/**
 * PermissionMapper
 * @author Wang926454
 * @date 2018/8/31 14:42
 */
public interface PermissionMapper{
    /**
     * 根据Role查询Permission
     * @param roleDto
     * @return java.util.List<com.wang.model.PermissionDto>
     * @author Wang926454
     * @date 2018/8/31 11:30
     */
    List<Permission> findPermissionByRole(Role role);
}