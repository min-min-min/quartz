package com.chenjing.quartz.config;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by Chenjing on 2017/5/10.
 */
@Configuration
public class ScheduledConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MyScheduler myScheduler;

    @Autowired
    private MyJobFactory myJobFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            myScheduler.scheduleJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // this allows to update triggers in DB when updating settings in config file:
        //用于quartz集群,QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(myJobFactory);
        //用于quartz集群,加载quartz数据源
        //factory.setDataSource(dataSource);
        //QuartzScheduler 延时启动，应用启动完10秒后 QuartzScheduler 再启动
        factory.setStartupDelay(10);
        factory.setAutoStartup(true);
        factory.setConfigLocation(new ClassPathResource("application.properties"));
        return factory;
    }
}

