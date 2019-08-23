package com.hkkj.modules.sys.service;

import com.hkkj.modules.sys.model.RolePermission;
import com.hkkj.common.base.service.BaseService;

import java.util.List;

public interface RolePermissionService extends BaseService<RolePermission> {
    /**
     * 根据角色ID获取当前角色下所有权限ID集合
     * @param roleId    角色ID
     * @return
     * @throws Exception
     */
    List<Long> findListPermissionIdsByRoleId(Long roleId) throws Exception;
    /**
     * 根据角色ID获取当前角色下所有权限ID集合，以,分割
     * @param roleId    角色ID
     * @return
     * @throws Exception
     */
    String findPermissionIdsByRoleId(Long roleId) throws Exception;

    /**
     * 角色授权
     * @param roleId 角色ID
     * @param permissionIds 权限id集合
     */
    void saveOrUpdate(Long roleId, Long[] permissionIds);
}
