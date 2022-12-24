package cn.zzwtsy.pu;

import cn.zzwtsy.pu.database.DataBaseHelper;
import cn.zzwtsy.pu.init.InitDataBase;
import cn.zzwtsy.pu.listener.ListenerPrivateChatMessage;
import cn.zzwtsy.pu.listener.ListenerGroupMessage;
import cn.zzwtsy.pu.tools.CheckConfigFile;
import cn.zzwtsy.pu.init.InitConfig;
import cn.zzwtsy.pu.tools.LoadConfig;
import cn.zzwtsy.pu.tools.SaveConfig;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;


/**
 * pu校园
 *
 * @author zzwtsy
 * @since 2022/11/28
 */
public final class PuCampus extends JavaPlugin {
    public static final PuCampus INSTANCE = new PuCampus();

    private PuCampus() {
        super(new JvmPluginDescriptionBuilder("cn.zzwtsy.pu", "0.1.0")
                .name("pu-campus")
                .author("zzwtsy")
                .build());
    }

    @Override
    public void onEnable() {
        CheckConfigFile checkConfigFile = new CheckConfigFile();
        if (!checkConfigFile.checkConfigFile()) {
            if (new InitConfig().initConfig()) {
                getLogger().info("Init Config file successfully,Please modify the config file and try again");
            } else {
                getLogger().error("Init Config file failed");
            }
            LoadConfig.loadAllConfig();
        } else {
            LoadConfig.loadAllConfig();
        }
        if (!checkConfigFile.checkDataBaseFile()) {
            getLogger().info("The database file does not exist and the database is being created");
            DataBaseHelper.registerDataBase();
            new InitDataBase().initDataBase();
        } else {
            getLogger().info("Registering database");
            DataBaseHelper.registerDataBase();
        }
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        eventChannel.registerListenerHost(new ListenerGroupMessage());
        eventChannel.registerListenerHost(new ListenerPrivateChatMessage());
        getLogger().info("pu-campus Plugin loaded!");
    }

    @Override
    public void onDisable() {
        SaveConfig.saveAllConfig();
    }
}