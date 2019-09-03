package com.hkkj.modules.quotation.service;

import com.github.pagehelper.PageInfo;
import com.hkkj.common.base.service.BaseService;
import com.hkkj.modules.quotation.model.Quotation;

/**
 * @author ldw
 * @create 2019-08-25 15:07
 */
public interface QuotationService extends BaseService<Quotation> {

    PageInfo<Quotation> findPage(Integer pageNum, Integer pageSize, String code, String customer, String salesman, String startTime, String endTime);
}
