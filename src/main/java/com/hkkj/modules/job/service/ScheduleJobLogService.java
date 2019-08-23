package com.hkkj.modules.job.service;

import com.github.pagehelper.PageInfo;
import com.hkkj.modules.job.model.ScheduleJobLog;
import com.hkkj.common.base.service.BaseService;

public interface ScheduleJobLogService extends BaseService<ScheduleJobLog> {
    /**
     * 根据ID分页查询调度任务历史
     * @param pageNum
     * @param pageSize
     * @param jobName
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<ScheduleJobLog> findPage(Integer pageNum, Integer pageSize, Long jobId, String jobName, String startTime, String endTime);
}
