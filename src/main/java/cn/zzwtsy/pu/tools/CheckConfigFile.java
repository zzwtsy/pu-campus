package cn.zzwtsy.pu.tools;

import java.io.File;

import static cn.zzwtsy.pu.tools.DataBaseStatic.DB_FILE_FULL_PATH;
import static cn.zzwtsy.pu.tools.DataBaseStatic.DB_FILE_PATH;
import static cn.zzwtsy.pu.tools.MyStatic.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.PATH_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.SETTING_FILE_NAME;


/**
 * 检查配置文件
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class CheckConfigFile {
    /**
     * 检查设置文件是否存在
     *
     * @return boolean
     */
    public boolean checkSettingFile() {
        File settingFile = new File("config/" + PATH_NAME + "/" + SETTING_FILE_NAME + ".json");
        return settingFile.exists();
    }

    /**
     * 检查命令文件是否存在
     *
     * @return boolean
     */
    public boolean checkCommandFile() {
        File commandFile = new File("config/" + PATH_NAME + "/" + COMMAND_FILE_NAME + ".json");
        return commandFile.exists();
    }

    /**
     * 检查数据库文件是否存在,不存在则创建数据库文件夹
     *
     * @return boolean
     */
    public boolean checkDataBaseFile() {
        if (new File(DB_FILE_FULL_PATH).exists()) {
            return true;
        } else {
            return new File(DB_FILE_PATH).mkdirs();
        }
    }
}
