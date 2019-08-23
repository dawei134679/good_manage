package com.hkkj.modules.cms.service;

import com.hkkj.modules.cms.model.ContentCat;
import com.hkkj.modules.sys.vo.TreeNode;
import com.hkkj.common.base.service.BaseService;

import java.util.List;

public interface ContentCatService extends BaseService<ContentCat> {
    /**
     * 查询新闻分类
     * @return
     */
    List<ContentCat> findListNewsCat();

    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();
}
