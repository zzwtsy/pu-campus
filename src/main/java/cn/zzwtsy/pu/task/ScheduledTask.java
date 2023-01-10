package cn.zzwtsy.pu.task;

/**
 * 定时任务
 *
 * @author zzwtsy
 * @since 2023/01/10
 */
public class ScheduledTask implements Runnable {
    long userQqId;

    /**
     * 计划任务
     *
     * @param userQqId 用户qq号
     */
    public ScheduledTask(long userQqId) {
        this.userQqId = userQqId;
    }

    @Override
    public void run() {
        System.out.println(userQqId);
    }
}
