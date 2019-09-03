package com.hkkj.modules.quotation.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hkkj.common.base.service.impl.BaseServiceImpl;
import com.hkkj.modules.quotation.model.Qcommodity;
import com.hkkj.modules.quotation.service.QcommodityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ldw
 * @create 2019-08-30 15:07
 */
@Transactional
@Service
public class QcommodityServiceImpl extends BaseServiceImpl<Qcommodity> implements QcommodityService {

    @Override
    public PageInfo<Qcommodity> findPage(Integer pageNum, Integer pageSize) {
        Example example = new Example(Qcommodity.class);
        Example.Criteria criteria = example.createCriteria();
       /* if (StringUtils.isNotEmpty(model)) {
            criteria.andLike("model", "%" + model.trim() + "%");
        }
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", "%" + name.trim() + "%");
        }
        if (StringUtils.isNotEmpty(spareNo)) {
            criteria.andLike("spareNo", "%" + spareNo.trim() + "%");
        }
        if (StringUtils.isNotEmpty(drawingNumber)) {
            criteria.andLike("drawingNumber", "%" + drawingNumber.trim() + "%");
        }
        if (StringUtils.isNotEmpty(specification)) {
            criteria.andLike("specification", "%" + specification.trim() + "%");
        }
        if (StringUtils.isNotEmpty(supplier)) {
            criteria.andLike("supplier", "%" + supplier.trim() + "%");
        }*/
        //排序
        example.orderBy("createTime").desc();
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<Qcommodity> qcommodityList = this.selectByExample(example);
        return new PageInfo<Qcommodity>(qcommodityList);
    }

    @Override
    public List<Qcommodity> getQcommodityByQid(Long qid) {
        Example example = new Example(Qcommodity.class);
        Example.Criteria criteria = example.createCriteria();
        example.orderBy("createTime").desc();
        if (qid != null) {
            criteria.andEqualTo("qid",qid);
        }
        List<Qcommodity> qcommodityList = this.selectByExample(example);
        return qcommodityList;
    }
}
