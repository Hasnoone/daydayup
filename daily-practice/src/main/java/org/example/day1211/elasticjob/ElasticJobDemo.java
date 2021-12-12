package org.example.day1211.elasticjob;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

public class ElasticJobDemo {
    public static void main(String[] args) {
        //配置分布式协调服务
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration("192.168.81.128:2181", "data-archive-job");
        ZookeeperRegistryCenter zookeeperRegistryCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
        zookeeperRegistryCenter.init();

        //配置任务 (时间时间 定时任务逻辑 调度器)
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration
                .newBuilder("archive-job", "*/2 * * * * ?", 3)
                .shardingItemParameters("0=bachelor,1=master,2=doctor")
                .build();

        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, BackupJob.class.getName());

        JobScheduler jobScheduler = new JobScheduler(zookeeperRegistryCenter, LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build());
        jobScheduler.init();
    }
}