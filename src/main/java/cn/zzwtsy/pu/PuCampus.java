package cn.zzwtsy.pu;

import cn.zzwtsy.pu.database.DataBaseHelper;
import cn.zzwtsy.pu.init.InitConfig;
import cn.zzwtsy.pu.init.InitDataBase;
import cn.zzwtsy.pu.listener.ListenerGroupMessage;
import cn.zzwtsy.pu.listener.ListenerPrivateChatMessage;
import cn.zzwtsy.pu.service.TimedTaskService;
import cn.zzwtsy.pu.tools.LoadConfig;
import cn.zzwtsy.pu.tools.SaveConfig;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;

import static cn.zzwtsy.pu.tools.Consts.settingBean;
import static cn.zzwtsy.pu.tools.Tools.checkCommandFile;
import static cn.zzwtsy.pu.tools.Tools.checkDataBaseFile;
import static cn.zzwtsy.pu.tools.Tools.checkSettingFile;


/**
 * pu校园
 *
 * @author zzwtsy
 * @since 2022/11/28
 */
public final class PuCampus extends JavaPlugin {
    public static final PuCampus INSTANCE = new PuCampus();

    private PuCampus() {
        super(new JvmPluginDescriptionBuilder("cn.zzwtsy.pu", "0.1.1")
                .name("pu-campus")
                .author("zzwtsy")
                .build());
    }

    @Override
    public void onEnable() {
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
        //检测设置配置文件是否存在
        if (!checkSettingFile()) {
            if (initConfig.initSettingConfig()) {
                getLogger().error("初始化设置文件成功，请修改 setting.json 配置文件后重启插件");
                getLogger().error("Init Setting file successfully,Please modify the config file and try again");
            } else {
                getLogger().error("Init Setting file failed");
            }
        }
        // 加载全部配置文件
        LoadConfig.loadAllConfig();
        //注册监听事件
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        eventChannel.registerListenerHost(new ListenerGroupMessage());
        eventChannel.registerListenerHost(new ListenerPrivateChatMessage());
        String doNotStartTimedTask = "0";
        if (!doNotStartTimedTask.equals(settingBean.getTimedTaskTime())) {
            //启动定时任务
            new TimedTaskService().start();
        }
        getLogger().info("pu-campus Plugin loaded!");
    }

    @Override
    public void onDisable() {
        //插件关闭时保存配置文件
        if (SaveConfig.saveAllConfig()) {
            PuCampus.INSTANCE.getLogger().info("Save All config files success");
        } else {
            PuCampus.INSTANCE.getLogger().info("Save All config files failed");
        }
    }
}