package org.example.day1211.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DemoJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("这是一份定时任务");
    }
}
