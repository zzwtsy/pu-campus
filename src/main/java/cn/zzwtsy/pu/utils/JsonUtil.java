package cn.zzwtsy.pu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json 工具类
 *
 * @author zzwtsy
 * @since 2023/02/10
 */
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static JsonNode fromJson(String content) throws JsonProcessingException {
        return MAPPER.readTree(content);
    }
}
