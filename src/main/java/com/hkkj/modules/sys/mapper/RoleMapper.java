package com.hkkj.modules.sys.mapper;

import com.hkkj.modules.sys.model.Role;
import com.hkkj.common.base.mapper.BaseMapper;

public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户ID查询角色对象信息
     * @param userId
     * @return
     */
    Role findByUserId(Long userId);
}
