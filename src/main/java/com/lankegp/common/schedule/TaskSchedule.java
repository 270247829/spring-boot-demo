package com.lankegp.common.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by liugongrui on 2018/1/17.
 * <p>
 * 日常任务定时
 */
@Component
public class TaskSchedule {


    /**
     * 每天0时创建任务记录
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void executeTask() {

    }

}
