package cn.zzwtsy.pu.command;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.UserConfig;
import cn.zzwtsy.pu.service.LoginService;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import net.mamoe.mirai.console.permission.Permission;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 用户命令
 *
 * @author zzwtsy
 * @since 2022/11/30
 */
public class Login extends JSimpleCommand {
    public static final Login INSTANCE = new Login();

    private Login() {
        super(PuCampus.INSTANCE, "login");
        setDescription("登录");
        setPrefixOptional(true);
        setPermission(Permission.getRootPermission());
    }

    @Handler
    public void onCommand(@NotNull CommandSender sender, @NotNull String userName, @NotNull String password) {
        Bot bot = Bot.getInstance(UserConfig.INSTANCE.getBotId());
        // 补全用户密码邮箱后缀
        String passwordFull = password + UserConfig.INSTANCE.getEmailSuffix();
        try {
            if (new LoginService().getUserToken(userName, passwordFull)) {
                bot.getGroup(UserConfig.INSTANCE.getGroupId()).sendMessage("登录成功:)");
            } else {
                bot.getGroup(UserConfig.INSTANCE.getGroupId()).sendMessage("登录失败'^'");
            }
        } catch (IOException e) {
            bot.getGroup(UserConfig.INSTANCE.getGroupId()).sendMessage("登录出现错误:\n" + e.getMessage());
            PuCampus.INSTANCE.getLogger().error("登录失败", e);
        }
    }
}
