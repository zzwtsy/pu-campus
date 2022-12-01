package cn.zzwtsy.pu.tools;

import java.io.File;

import static cn.zzwtsy.pu.tools.MyStatic.*;

/**
 * 检查配置文件
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class CheckConfigFile {
    /**
     * 检查配置文件是否存在
     *
     * @return boolean
     */
    public boolean check() {
        File userFile = new File("config/" + PATH_NAME + "/" + USER_CONFIG_FILE_NAME + ".json");
        File commandFile = new File("config/" + PATH_NAME + "/" + COMMAND_FILE_NAME + ".json");
        return userFile.exists() && commandFile.exists();
    }
}
