package cn.zzwtsy.pu;

import cn.zzwtsy.pu.tools.InitConfig;
import cn.zzwtsy.pu.tools.SaveConfig;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

import java.io.IOException;

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
        InitConfig initConfig = new InitConfig();
        getLogger().info("Init User Config");
        initConfig.initUserConfig();
        getLogger().info("Init Command Config");
        initConfig.initCommandConfig();
        SaveConfig saveConfig = new SaveConfig();
        try {
            saveConfig.saveUserConfig();
        } catch (IOException e) {
            getLogger().error("Failed to save UserConfig" + e);
        }
        try {
            saveConfig.saveCommandConfig();
        } catch (IOException e) {
            getLogger().error("Failed to save Command" + e);
        }
        getLogger().info("pu-campus Plugin loaded!");
    }
}