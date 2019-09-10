package com.hkkj.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hkkj.common.base.service.impl.BaseServiceImpl;
import com.hkkj.modules.sys.model.Customer;
import com.hkkj.modules.sys.service.CustomerService;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Transactional
@Service
public class CusomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {

    @Override
    public PageInfo<Customer> findPage(Integer pageNum, Integer pageSize, String name, String startTime, String endTime) {
        Example example = new Example(Customer.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", "%" + name.trim() + "%");
        }
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", "%" + name.trim() + "%");
        }
        if (StrUtil.isNotEmpty(startTime)) {
            criteria.andGreaterThanOrEqualTo("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)));
        }
        if (StrUtil.isNotEmpty(endTime)) {
            criteria.andLessThanOrEqualTo("createTime", DateUtil.endOfDay(DateUtil.parse(endTime)));
        }
        //排序
        example.orderBy("createTime").desc();
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<Customer> customerList = this.selectByExample(example);
        return new PageInfo<Customer>(customerList);
    }

    @Override
    public List<Customer> getCustomerList() {
        Example example = new Example(Customer.class);
        Example.Criteria criteria = example.createCriteria();
        example.orderBy("createTime").desc();
        List<Customer> customerList = this.selectByExample(example);
        return customerList;
    }
}
