package cn.zzwtsy.pu;

import cn.zzwtsy.pu.command.Login;
import cn.zzwtsy.pu.tools.CheckConfigFile;
import cn.zzwtsy.pu.tools.InitConfig;
import cn.zzwtsy.pu.tools.LoadConfig;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

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
        if (!new CheckConfigFile().check()) {
            if (new InitConfig().initConfig()) {
                getLogger().info("Init Config file successfully,Please modify the config file and try again");
            } else {
                getLogger().error("Init Config file failed");
            }
        } else {
            new LoadConfig().loadAllConfig();
        }
        CommandManager.INSTANCE.registerCommand(Login.INSTANCE, false);
        getLogger().info("pu-campus Plugin loaded!");
    }
}