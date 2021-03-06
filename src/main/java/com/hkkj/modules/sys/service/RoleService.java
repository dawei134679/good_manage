package com.hkkj.modules.sys.service;

import com.hkkj.modules.sys.model.Role;
import com.hkkj.common.base.service.BaseService;

public interface RoleService extends BaseService<Role> {
    /**
     * 根据角色名称查询角色对象信息
     * @param name
     * @return
     */
    Role findByName(String name);
    /**
     * 根据用户ID查询角色对象信息
     * @param userId
     * @return
     */
    Role findByUserId(Long userId);

    /**
     * 根据角色ID删除角色并级联删除该角色和权限的关联信息
     * @param roleId
     * @return
     */
    Boolean deleteRoleAndRolePermissionByRoleId(Long roleId);
}
