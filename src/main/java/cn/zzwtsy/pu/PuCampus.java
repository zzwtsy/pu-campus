package cn.zzwtsy.pu;

import cn.zzwtsy.pu.listener.ListenerGroupMessage;
import cn.zzwtsy.pu.tools.CheckConfigFile;
import cn.zzwtsy.pu.tools.InitConfig;
import cn.zzwtsy.pu.tools.LoadConfig;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;

import static cn.zzwtsy.pu.tools.MyStatic.userConfig;

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
        boolean check = new CheckConfigFile().check();
        if (!check) {
            if (new InitConfig().initConfig()) {
                getLogger().info("Init Config file successfully,Please modify the config file and try again");
            } else {
                getLogger().error("Init Config file failed");
            }
            new LoadConfig().loadAllConfig();
        } else {
            new LoadConfig().loadAllConfig();
        }
        long botId = userConfig.getBotId();
        EventChannel<Event> filter = GlobalEventChannel.INSTANCE.filter(ev -> ev instanceof BotEvent && ((BotEvent) ev).getBot().getId() == botId);
        EventChannel<Event> eventChannel = filter.parentScope(this);
        eventChannel.registerListenerHost(new ListenerGroupMessage());
        getLogger().info("pu-campus Plugin loaded!");
    }
}