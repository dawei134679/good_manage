package com.hkkj.modules.commodity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hkkj.common.base.service.BaseService;
import com.hkkj.common.base.service.impl.BaseServiceImpl;
import com.hkkj.modules.cms.model.ContentCat;
import com.hkkj.modules.commodity.mapper.CommodityMapper;
import com.hkkj.modules.commodity.model.Commodity;
import com.hkkj.modules.commodity.service.CommodityService;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommdityServiceImpl extends BaseServiceImpl<Commodity> implements CommodityService{

    @Override
    public PageInfo<Commodity> findPage(Integer pageNum, Integer pageSize, String name, String code, String brand, String supplier) {
        Example example = new Example(Commodity.class);
        Example.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotEmpty(name)){
            criteria.andLike("name", "%"+name+"%");
        }
        if(StringUtils.isNotEmpty(code)){
            criteria.andLike("code", "%"+code+"%");
        }
        if(StringUtils.isNotEmpty(brand)){
            criteria.andLike("brand", "%"+brand+"%");
        }
        if(StringUtils.isNotEmpty(supplier)){
            criteria.andLike("supplier", "%"+supplier+"%");
        }
        //排序
        example.orderBy("createTime").desc();
        //分页
        PageHelper.startPage(pageNum,pageSize);
        List<Commodity> commodityList = this.selectByExample(example);
        return new PageInfo<Commodity>(commodityList);
    }
}
