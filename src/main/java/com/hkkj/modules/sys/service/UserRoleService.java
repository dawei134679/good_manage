package com.hkkj.modules.sys.service;

import com.hkkj.modules.sys.model.UserRole;
import com.hkkj.common.base.service.BaseService;

public interface UserRoleService extends BaseService<UserRole> {
    /**
     * 根据用户ID和角色ID查询用户和角色绑定信息
     * @param userId
     * @param roleId
     * @return
     */
    UserRole findByUserIdAndRoleId(Long userId, Long roleId);
}
