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
    private ScheduledExecutorService scheduler;

    /**
     * 启动定时任务
     *
     * @return {@link String}
     */
    public String start() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        long delayTime;
        try {
            delayTime = calculateScheduledDelayTime();
            scheduler.scheduleAtFixedRate(new ScheduledTask(), delayTime, 86400, TimeUnit.SECONDS);
            PuCampus.INSTANCE.getLogger().info("已启动定时任务，延时" + delayTime + "秒后开始发送消息");
        } catch (ParseException e) {
            PuCampus.INSTANCE.getLogger().error("启动定时任务失败", e);
            return "启动定时任务失败" + e.getMessage();
        }
        int anHour = 3600;
        if (delayTime > anHour || delayTime == anHour) {
            return "已启动定时任务，延时" + (delayTime / 3600) + "小时后开始发送消息";
        }
        int aMinute = 60;
        if (delayTime > aMinute || delayTime == aMinute) {
            return "已启动定时任务，延时" + (delayTime / 60) + "分钟后开始发送消息";
        }
        return "已启动定时任务，延时" + delayTime + "秒后开始发送消息";
    }

    /**
     * 停止定时任务
     */
    public void stop() {
        scheduler.shutdownNow();
        PuCampus.INSTANCE.getLogger().info("已停止定时任务");
    }

}
