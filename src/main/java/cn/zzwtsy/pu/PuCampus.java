package cn.zzwtsy.pu;

import cn.zzwtsy.pu.data.Setting;
import cn.zzwtsy.pu.database.DataBaseHelper;
import cn.zzwtsy.pu.init.InitConfig;
import cn.zzwtsy.pu.init.InitDataBase;
import cn.zzwtsy.pu.listener.ListenerGroupMessage;
import cn.zzwtsy.pu.listener.ListenerPrivateChatMessage;
import cn.zzwtsy.pu.service.TimedTaskService;
import cn.zzwtsy.pu.tools.LoadConfig;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.GroupEvent;

import static cn.zzwtsy.pu.tools.Tools.checkCommandFile;
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
        super(new JvmPluginDescriptionBuilder("cn.zzwtsy.pu", "0.1.2")
                .name("pu-campus")
                .author("zzwtsy")
                .build()
        );
    }

    @Override
    public void onEnable() {
        reloadPluginConfig(Setting.INSTANCE);
        //检测数据库文件是否存在
        if (!checkDataBaseFile()) {
            getLogger().info("The database file does not exist and the database is being created");
            DataBaseHelper.registerDataBase();
            new InitDataBase().initDataBase();
        } else {
            getLogger().info("Registering database");
            DataBaseHelper.registerDataBase();
        }
        InitConfig initConfig = new InitConfig();
        //检测命令配置文件是否存在
        if (!checkCommandFile()) {
            if (initConfig.initCommandConfig()) {
                getLogger().info("Init Command file successfully,Please modify the config file and try again");
            } else {
                getLogger().error("Init Command file failed");
            }
        }
        // 加载全部配置文件
        LoadConfig.loadAllConfig();
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
        String doNotStartTimedTask = "0";
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
    }
}