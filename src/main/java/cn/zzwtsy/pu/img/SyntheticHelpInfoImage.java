package cn.zzwtsy.pu.img;


import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.tools.DataBaseStatic;
import cn.zzwtsy.pu.tools.MyStatic;
import cn.zzwtsy.pu.tools.Tools;
import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.LineAlign;
import com.freewayso.image.combiner.enums.OutputFormat;

import java.awt.Font;
import java.net.URL;
import java.util.HashMap;


/**
 * 合成帮助信息图像
 *
 * @author zzwtsy
 * @since 2023/01/13
 */
public class SyntheticHelpInfoImage {

    /**
     * 合成qq群帮助信息图片
     */
    public void syntheticGroupHelpInfoImage(HashMap<String, String> command) throws Exception {
        syntheticImage(command, "group", MyStatic.GROUP_IMG_PATH);
    }

    /**
     * 合成私聊帮助信息图片
     *
     * @param command 命令
     * @throws Exception 异常
     */
    public void syntheticPrivateHelpInfoImage(HashMap<String, String> command) throws Exception {
        syntheticImage(command, "private", MyStatic.PRIVATE_IMG_PATH);
    }

    /**
     * 合成管理员帮助信息图片
     *
     * @param command 命令
     * @throws Exception 异常
     */
    public void syntheticAdminHelpInfoImage(HashMap<String, String> command) throws Exception {
        syntheticImage(command, "admin", MyStatic.ADMIN_IMG_PATH);
    }

    /**
     * 合成图像
     *
     * @param command     命令
     * @param imgName     生成图片名称
     * @param imgFilePath 图片文件路径
     * @throws Exception 异常
     */
    private void syntheticImage(HashMap<String, String> command, String imgName, String imgFilePath) throws Exception {
        int fontSize = 16;
        Font fontName = Tools.getFont("/static/font/HarmonyOS_Sans_SC_Regular.ttf", fontSize);
        URL tempPath = this.getClass().getResource(imgFilePath);
        if (tempPath == null) {
            PuCampus.INSTANCE.getLogger().error("生成帮助信息图片失败");
            return;
        }
        String filePath = tempPath.toString();
        ImageCombiner combiner = new ImageCombiner(filePath, OutputFormat.PNG);
        int commandMarginLeft = 73;
        int descriptionMarginLeft = 515;
        int height = 165;
        for (String key : command.keySet()) {
            combiner.addTextElement(key, fontSize, commandMarginLeft, height)
                    .setFont(fontName)
                    .setAutoBreakLine(220, 2, LineAlign.Left);
            combiner.addTextElement(command.get(key), fontSize, descriptionMarginLeft, height)
                    .setFont(fontName)
                    .setAutoBreakLine(220, 2, LineAlign.Left);
            height += 66;
        }
        //图片圆角
        combiner.setCanvasRoundCorner(16);
        combiner.combine();
        combiner.save(DataBaseStatic.PLUGIN_DATA_FILE_PATH + imgName + ".png");
    }
}
