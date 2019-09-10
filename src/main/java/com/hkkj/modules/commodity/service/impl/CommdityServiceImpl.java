package com.hkkj.modules.commodity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hkkj.common.base.service.impl.BaseServiceImpl;
import com.hkkj.modules.commodity.model.Commodity;
import com.hkkj.modules.commodity.service.CommodityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ldw
 * @create 2019-06-05 15:07
 */
@Transactional
@Service
public class CommdityServiceImpl extends BaseServiceImpl<Commodity> implements CommodityService {

    @Override
    public PageInfo<Commodity> findPage(Integer pageNum, Integer pageSize, String model, String name, String spareNo, String drawingNumber, String specification, String supplier) {
        Example example = new Example(Commodity.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(model)) {
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
        }
        //排序
        example.orderBy("createTime").desc();
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<Commodity> commodityList = this.selectByExample(example);
        return new PageInfo<Commodity>(commodityList);
    }

    @Override
    public List<Commodity> getAllCommodityList() {
        Example example = new Example(Commodity.class);
        Example.Criteria criteria = example.createCriteria();
        example.orderBy("createTime").desc();
        List<Commodity> commodityList = this.selectByExample(example);
        return commodityList;
    }
}
