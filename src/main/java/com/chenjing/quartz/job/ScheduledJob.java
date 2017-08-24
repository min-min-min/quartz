package com.chenjing.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Chenjing on 2017/5/10.
 * 该定时任务是把orderParent的一千条数据发送到队列order_parent
 */
@Component
public class ScheduledJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date());
    }
}
