package com.hkkj.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.hkkj.common.base.service.BaseService;
import com.hkkj.modules.sys.model.Customer;

import java.util.List;

public interface CustomerService extends BaseService<Customer> {
    /**
     *
     * @param pageNum  当前页码
     * @param pageSize  每页显示条数
     * @param name 客户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @throws Exception
     */
    PageInfo<Customer> findPage(Integer pageNum, Integer pageSize, String name, String startTime, String endTime) throws Exception;

    /**
     * 获全部客户列表
     */
    List<Customer> getCustomerList();
}
