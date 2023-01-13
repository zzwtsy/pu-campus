package cn.zzwtsy.pu.img;


import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.Command;
import cn.zzwtsy.pu.utils.ConfigHelper;
import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.LineAlign;
import com.freewayso.image.combiner.enums.OutputFormat;
import java.net.URL;
import java.util.HashMap;

import static cn.zzwtsy.pu.tools.MyStatic.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.PATH_NAME;

/**
 * 合成帮助信息图像
 *
 * @author zzwtsy
 * @since 2023/01/13
 */
public class SyntheticHelpInfoImage {
    private final int fontSize = 16;

    /**
     * 合成qq群帮助信息图像
     */
    public void syntheticGroupHelpInfoImage(HashMap<String, String> command) throws Exception {
        URL tempPath = this.getClass().getResource("/img/help/group.png");
        if (tempPath == null) {
            PuCampus.INSTANCE.getLogger().error("");
            return;
        }
        String filePath = tempPath.toString();
        String fontName = "HarmonyOS Sans SC";
        ImageCombiner combiner = new ImageCombiner(filePath, OutputFormat.PNG);
        int commandMarginLeft = 73;
        int descriptionMarginLeft = 515;
        int height = 165;
        for (String key : command.keySet()) {
            combiner.addTextElement(key, fontName, fontSize, commandMarginLeft, height);
            combiner.addTextElement(command.get(key), fontName, fontSize, descriptionMarginLeft, height).setAutoBreakLine(220, 2, LineAlign.Left);
            height += 66;
        }
        //图片圆角
        combiner.setCanvasRoundCorner(16);
        combiner.combine();
        combiner.save("test.png");
    }

    public static void main(String[] args) throws Exception {
        Command configFromFile = ConfigHelper.getConfigFromFile(PATH_NAME, COMMAND_FILE_NAME, Command.class);
        String eventListCommand = configFromFile.getCommandPrefix() + configFromFile.getGetCalendarEventList();
        String helpCommand = configFromFile.getCommandPrefix() + configFromFile.getHelp();
        String queryUserCreditInfoCommand = configFromFile.getCommandPrefix() + configFromFile.getQueryUserCreditInfo();
        String querySignInEventListCommand = configFromFile.getCommandPrefix() + configFromFile.getQuerySignInEventList();
        String querySignOutEventListCommand = configFromFile.getCommandPrefix() + configFromFile.getQuerySignOutEventList();
        String queryUserEventEndUnissuedCreditListCommand = configFromFile.getCommandPrefix() + configFromFile.getQueryUserEventEndUnissuedCreditList();
        HashMap<String, String> command = new HashMap<>(6);
        command.put(eventListCommand, "根据日期获取可参加活动列表");
        command.put(queryUserCreditInfoCommand, "查询个人学分信息");
        command.put(queryUserEventEndUnissuedCreditListCommand, "获取活动已结束未发放学分活动");
        command.put(querySignInEventListCommand, "获取需要签到的活动");
        command.put(querySignOutEventListCommand, "获取需要签退的活动");
        command.put(helpCommand, "获取帮助信息");
        new SyntheticHelpInfoImage().syntheticGroupHelpInfoImage(command);
    }
}
