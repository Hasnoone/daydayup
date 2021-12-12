package org.example.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class BackupJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        int shardingItem = shardingContext.getShardingItem();
        System.out.println("当前分片" + shardingItem);
        String shardingParameter = shardingContext.getShardingParameter();
        System.out.println("分片参数" + shardingParameter);
        System.out.println("elastic-job-lite 定时执行的任务");
    }
}
