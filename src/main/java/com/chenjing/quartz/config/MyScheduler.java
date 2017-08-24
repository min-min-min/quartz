package com.chenjing.quartz.config;


import com.chenjing.quartz.job.ScheduledJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by Chenjing on 2017/5/10.
 */
@Component
public class MyScheduler {

    @Autowired
    @Qualifier("schedulerFactoryBean")
    private SchedulerFactoryBean schedulerFactoryBean;

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        startOrderParentJob(scheduler);
    }
    private void startOrderParentJob(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class) .withIdentity("SyncOrderJob", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("SyncOrderTrigger", "group1") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

}
