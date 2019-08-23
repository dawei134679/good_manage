package com.hkkj.modules.job.mapper;

import com.hkkj.modules.job.model.ScheduleJob;
import com.hkkj.common.base.mapper.BaseMapper;

import java.util.List;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {
    /**
     * 注:通用mapper 不支持@PostConstruct
     * 查询所有任务
     * @return
     */
    List<ScheduleJob> findList();
}
