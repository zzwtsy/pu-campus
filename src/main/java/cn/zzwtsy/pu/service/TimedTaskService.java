package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.task.ScheduledTask;

import java.text.ParseException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static cn.zzwtsy.pu.tools.Tools.calculateScheduledDelayTime;

/**
 * 定时任务
 *
 * @author zzwtsy
 * @since 2023/01/12
 */
public class TimedTaskService {
    private final ScheduledExecutorService scheduler;

    public TimedTaskService() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }


    /**
     * 启动定时任务
     */
    public void start() {
        try {
            long delayTime = calculateScheduledDelayTime();
            scheduler.scheduleAtFixedRate(new ScheduledTask(), delayTime, 86400, TimeUnit.SECONDS);
            PuCampus.INSTANCE.getLogger().info("已启动定时任务，延时" + delayTime / 60 + "分钟后开始发送消息");
        } catch (ParseException e) {
            PuCampus.INSTANCE.getLogger().error("启动定时任务失败", e);
        }
    }

    /**
     * 停止定时任务
     */
    public void stop() {
        scheduler.shutdown();
        PuCampus.INSTANCE.getLogger().info("已停止定时任务");
    }
}
