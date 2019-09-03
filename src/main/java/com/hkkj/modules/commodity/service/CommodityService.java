package com.hkkj.modules.commodity.service;

import com.github.pagehelper.PageInfo;
import com.hkkj.common.base.service.BaseService;
import com.hkkj.modules.commodity.model.Commodity;

/**
 * @author ldw
 * @create 2019-06-05 15:07
 */
public interface CommodityService extends BaseService<Commodity> {

    PageInfo<Commodity> findPage(Integer pageNum, Integer pageSize, String model, String name, String spareNo, String drawingNumber, String specification, String supplier);
}
