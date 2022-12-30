package cn.zzwtsy.pu.utils;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

/**
 * HttpHelper
 *
 * @author zzwtsy
 * @since 2022/11/28
 */
public class HttpHelper {
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    /**
     * 发送GET请求
     *
     * @param url     发送请求的URL
     * @param headers 请求头
     * @return 远程资源的响应结果
     */
    @NotNull
    public static String sendGet(String url, Headers headers) throws IOException {
        Request request;
        Response response = null;
        String responseBody;
        request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .headers(headers)
                .build();
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            responseBody = Objects.requireNonNull(response.body()).string();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * 发送POST请求
     *
     * @param url         URL
     * @param headers     请求头
     * @param requestBody 请求体
     * @return 远程资源的响应结果
     */
    @NotNull
    public static String sendPost(String url, Headers headers, okhttp3.RequestBody requestBody) throws IOException {
        Request request;
        Response response = null;
        String responseBody;
        request = new okhttp3.Request.Builder()
                .url(url)
                .post(requestBody)
                .headers(headers)
                .build();
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            responseBody = Objects.requireNonNull(response.body()).string();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }
}
