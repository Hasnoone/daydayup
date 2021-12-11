package org.example.day1211.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzMain {

    /**
     * 创建任务调度器
     * @return
     */
    private static Scheduler createScheduler() {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            return schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 创建任务
     * @return
     */
    public static JobDetail createJobDetail() {
        JobBuilder jobBuilder = JobBuilder.newJob(DemoJob.class);//@TODO 自定义任务类
        JobBuilder jobBuilder1 = jobBuilder.withIdentity("testJob", "myJob");
        return jobBuilder1.build();
    }


    public static Trigger createTrigger() {
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("testTrigger", "myTrigger")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?")).build();
        return cronTrigger;
    }






    public static void main(String[] args) throws SchedulerException {
        //创建任务调度器
        Scheduler scheduler = createScheduler();
        //创建任务
        JobDetail jobDetail = createJobDetail();
        //创建任务时间触发器
        Trigger trigger = createTrigger();
        //使用任务调度器根据时间触发任务
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }


}
