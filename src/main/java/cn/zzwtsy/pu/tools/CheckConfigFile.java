package cn.zzwtsy.pu.tools;

import java.io.File;

import static cn.zzwtsy.pu.tools.MyStatic.COMMAND_FILE_PATH;
import static cn.zzwtsy.pu.tools.MyStatic.USER_CONFIG_FILE_PATH;

/**
 * 检查配置文件
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class CheckConfigFile {
    /**
     * 检查
     *
     * @return boolean
     */
    public boolean check() {
        File userFile = new File("config/pu-campus/" + USER_CONFIG_FILE_PATH + ".json");
        File commandFile = new File("config/pu-campus/" + COMMAND_FILE_PATH + ".json");
        return userFile.exists() && commandFile.exists();
    }
}
