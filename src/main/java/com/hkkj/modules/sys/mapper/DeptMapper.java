package com.hkkj.modules.sys.mapper;

import com.hkkj.modules.sys.model.Dept;
import com.hkkj.modules.sys.vo.TreeNode;
import com.hkkj.common.base.mapper.BaseMapper;

import java.util.List;

/**
 * @package: com.hkkj.modules.sys.mapper
 * @description:
 * @version: V1.0.0
 */
public interface DeptMapper extends BaseMapper<Dept>{

    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();
}
