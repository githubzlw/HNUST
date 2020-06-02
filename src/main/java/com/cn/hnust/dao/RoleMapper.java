package com.cn.hnust.dao;

import com.cn.hnust.model.Role;
import com.cn.hnust.pojo.User;
import java.util.List;

/**
 * RoleMapper
 * @author Wang926454
 * @date 2018/8/31 14:42
 */
public interface RoleMapper{
    /**
     * 根据User查询Role
     * @param userDto
     * @return java.util.List<com.wang.model.RoleDto>
     * @author Wang926454
     * @date 2018/8/31 11:30
     */
    List<Role> findRoleByUser(User user);
}