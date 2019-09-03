package com.hkkj.modules.quotation.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hkkj.common.base.service.impl.BaseServiceImpl;
import com.hkkj.modules.quotation.model.Quotation;
import com.hkkj.modules.quotation.service.QuotationService;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ldw
 * @create 2019-08-25 15:07
 */
@Transactional
@Service
public class QuotationServiceImpl extends BaseServiceImpl<Quotation> implements QuotationService {

    @Override
    public PageInfo<Quotation> findPage(Integer pageNum, Integer pageSize, String code, String customer, String salesman, String startTime, String endTime) {
        Example example = new Example(Quotation.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(code)) {
            criteria.andLike("code", "%" + code.trim() + "%");
        }
        if (StringUtils.isNotEmpty(customer)) {
            criteria.andLike("customer", "%" + customer.trim() + "%");
        }
        if (StringUtils.isNotEmpty(salesman)) {
            criteria.andLike("salesman", "%" + salesman.trim() + "%");
        }
        if(StrUtil.isNotEmpty(startTime)){
            criteria.andGreaterThanOrEqualTo("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)));
        }
        if(StrUtil.isNotEmpty(endTime)){
            criteria.andLessThanOrEqualTo("createTime", DateUtil.endOfDay(DateUtil.parse(endTime)));
        }
        //排序
        example.orderBy("createTime").desc();
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<Quotation> quotationList = this.selectByExample(example);
        return new PageInfo<Quotation>(quotationList);
    }
}
