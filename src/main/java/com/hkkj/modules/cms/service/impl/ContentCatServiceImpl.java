package com.hkkj.modules.cms.service.impl;

import com.hkkj.modules.cms.mapper.ContentCatMapper;
import com.hkkj.modules.cms.model.ContentCat;
import com.hkkj.modules.cms.service.ContentCatService;
import com.hkkj.modules.sys.vo.TreeNode;
import com.hkkj.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class ContentCatServiceImpl extends BaseServiceImpl<ContentCat> implements ContentCatService {

    @Resource
    private ContentCatMapper contentCatMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ContentCat> findListNewsCat() {
        ContentCat contentCat = new ContentCat();
        contentCat.setParentName("新闻中心");
        return super.findListByWhere(contentCat);
    }

    @Transactional(readOnly=true)
    @Override
    public List<TreeNode> findTreeList() {
        return contentCatMapper.findTreeList();
    }
}
