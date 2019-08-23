package com.hkkj.modules.job.listener;

import com.hkkj.modules.job.service.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public class ScheduleJobListener {

    @Resource
    private ScheduleJobService scheduleJobService;

    /**
     * 项目启动时初始化
     */
    @PostConstruct
    public void init() {

        if (log.isInfoEnabled()) {
            log.info("初始化定时任务...,开始");
        }

        scheduleJobService.initScheduleJob();

        if (log.isInfoEnabled()) {
            log.info("初始化定时任务...,完成");
        }
    }
}
