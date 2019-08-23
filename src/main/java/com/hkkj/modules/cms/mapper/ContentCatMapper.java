package com.hkkj.modules.cms.mapper;

import com.hkkj.modules.cms.model.ContentCat;
import com.hkkj.modules.sys.vo.TreeNode;
import com.hkkj.common.base.mapper.BaseMapper;

import java.util.List;

public interface ContentCatMapper extends BaseMapper<ContentCat> {
    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();
}
