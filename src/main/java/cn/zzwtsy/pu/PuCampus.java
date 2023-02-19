package cn.zzwtsy.pu;

import cn.zzwtsy.pu.data.Command;
import cn.zzwtsy.pu.data.Setting;
import cn.zzwtsy.pu.database.DataBaseHelper;
import cn.zzwtsy.pu.init.InitDataBase;
import cn.zzwtsy.pu.listener.ListenerGroupMessage;
import cn.zzwtsy.pu.listener.ListenerPrivateChatMessage;
import cn.zzwtsy.pu.service.TimedTaskService;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.GroupEvent;

import static cn.zzwtsy.pu.tools.Tools.checkDataBaseFile;


/**
 * pu校园
 *
 * @author zzwtsy
 * @since 2022/11/28
 */
public final class PuCampus extends JavaPlugin {
    public static final PuCampus INSTANCE = new PuCampus();

    private PuCampus() {
        super(new JvmPluginDescriptionBuilder("cn.zzwtsy.pu", "0.2.0")
                .name("pu-campus")
                .author("zzwtsy")
                .build()
        );
    }

    @Override
    public void onEnable() {
        reloadPluginConfig(Setting.INSTANCE);
        reloadPluginConfig(Command.INSTANCE);
        //检测数据库文件是否存在
        if (!checkDataBaseFile()) {
            DataBaseHelper.registerDataBase();
            new InitDataBase().initDataBase();
        } else {
            DataBaseHelper.registerDataBase();
        }
        //过滤事件
        EventChannel<GroupEvent> groupEventEventChannel = GlobalEventChannel.INSTANCE
                .filterIsInstance(GroupEvent.class)
                .filter(groupEvent -> groupEvent.getGroup().getId() == Setting.INSTANCE.getGroupId());
        EventChannel<BotEvent> botEventEventChannel = GlobalEventChannel.INSTANCE
                .filterIsInstance(BotEvent.class)
                .filter(event -> event.getBot().getId() == Setting.INSTANCE.getBotId());
        //注册监听事件
        groupEventEventChannel.registerListenerHost(new ListenerGroupMessage());
        botEventEventChannel.registerListenerHost(new ListenerPrivateChatMessage());
        String doNotStartTimedTask = "";
        String timedTask = Setting.INSTANCE.getTimedTask();
        if (!doNotStartTimedTask.equals(timedTask)) {
            //启动定时任务
            String taskStatus = new TimedTaskService().startTimedTask(Setting.INSTANCE.getGroupId(), timedTask);
            getLogger().info(taskStatus);
        }
        getLogger().info("pu-campus Plugin loaded!");
    }

    @Override
    public void onDisable() {
        savePluginConfig(Setting.INSTANCE);
        savePluginConfig(Command.INSTANCE);
    }
}