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

    /**
     * 转换到 json
     *
     * @param content 内容
     * @return {@link String}
     * @throws JsonProcessingException json处理异常
     */
    public static String toJson(String content) throws JsonProcessingException {
        return MAPPER.writeValueAsString(content);
    }

    /**
     * 转换到格式化 json
     *
     * @param content 内容
     * @return {@link String}
     * @throws JsonProcessingException json处理异常
     */
    public static String toPrettyJson(String content) throws JsonProcessingException {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(content);
    }

    public static JsonNode fromJson(String content) throws JsonProcessingException {
        return MAPPER.readTree(content);
    }

    public static <T> T fromJson(String content, Class<T> type) throws JsonProcessingException {
        return MAPPER.readValue(content, type);
    }
}
