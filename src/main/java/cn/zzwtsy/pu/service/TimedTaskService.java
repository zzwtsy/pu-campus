package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.task.ScheduledTask;

import java.text.ParseException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static cn.zzwtsy.pu.tools.Consts.TASKS_MAP;
import static cn.zzwtsy.pu.tools.Consts.settingBean;
import static cn.zzwtsy.pu.tools.Tools.calculateScheduledDelayTime;

/**
 * 定时任务
 *
 * @author zzwtsy
 * @since 2023/01/12
 */
public class TimedTaskService {

    /**
     * 启动定时任务
     *
     * @return {@link String}
     */
    private String execute(long groupId) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        long delayTime;
        try {
            delayTime = calculateScheduledDelayTime();
            TASKS_MAP.put(groupId, scheduler.scheduleAtFixedRate(new ScheduledTask(), delayTime, 86400, TimeUnit.SECONDS));
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
     * 启动定时任务
     *
     * @return {@link String}
     */
    public String startTimedTask(long groupId) {
        if (!TASKS_MAP.containsKey(groupId)) {
            return execute(groupId);
        }
        ScheduledFuture<?> scheduledFuture = TASKS_MAP.get(groupId);
        if (!scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
            TASKS_MAP.remove(groupId);
        }
        return execute(groupId);
    }

    /**
     * 停止定时任务
     *
     * @return {@link String}
     */
    public String stopTimedTask(long groupId) {
        if (!TASKS_MAP.containsKey(groupId)) {
            return groupId + "没有定时任务";
        }
        ScheduledFuture<?> scheduledFuture = TASKS_MAP.get(groupId);
        if (scheduledFuture.isCancelled()) {
            return groupId + "定时任务没有启动";
        }
        if (scheduledFuture.cancel(true)) {
            TASKS_MAP.remove(groupId);
            return groupId + "定时任务已停止";
        }
        return "定时任务停止失败";
    }

    /**
     * 显示任务状态
     *
     * @return {@link String}
     */
    public String showTaskStatus() {
        String doNotStartTimedTask = "0";
        if (doNotStartTimedTask.equals(settingBean.getTimedTaskTime())) {
            return "没有定时任务运行";
        }
        StringBuilder sb = new StringBuilder();
        TASKS_MAP.keySet().forEach(key -> sb.append(key).append(":已有定时任务\n"));
        return sb.toString();
    }
}
