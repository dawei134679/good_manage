package com.hkkj.modules.quotation.service;

import com.github.pagehelper.PageInfo;
import com.hkkj.common.base.service.BaseService;
import com.hkkj.modules.quotation.model.Qcommodity;

import java.util.List;

/**
 * @author ldw
 * @create 2019-08-30 15:07
 */
public interface QcommodityService extends BaseService<Qcommodity> {

    PageInfo<Qcommodity> findPage(Integer pageNum, Integer pageSize);

    List<Qcommodity> getQcommodityByQid(Long qid);
}
