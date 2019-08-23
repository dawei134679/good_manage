package com.hkkj.modules.log.service;

import com.github.pagehelper.PageInfo;
import com.hkkj.modules.log.model.Log;
import com.hkkj.common.base.service.BaseService;

public interface LogService extends BaseService<Log> {

    /**
     * 分页查询日志列表
     * @param pageNum
     * @param pageSize
     * @param username
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<Log> findPage(Integer pageNum, Integer pageSize, String username, String startTime, String endTime);
}
