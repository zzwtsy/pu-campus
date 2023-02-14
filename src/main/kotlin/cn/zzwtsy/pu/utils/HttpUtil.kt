package cn.zzwtsy.pu.utils

import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.concurrent.TimeUnit

object HttpUtil {
    private val client = OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build()

    /**
     * 发送 Get 请求
     * @param [url] url
     * @param [headers] 请求头
     * @return [String?]
     */
    fun sendGet(url: String, headers: Headers): String {
        val request = Request.Builder()
            .get()
            .url(url)
            .headers(headers)
            .build()
        return client.newCall(request).execute().body?.string()!!
    }

    /**
     * 发送 Post 请求
     * @param [url] url
     * @param [headers] 请求头
     * @param [requestBody] 请求体
     * @return [String?]
     */
    fun sendPost(url: String, headers: Headers, requestBody: RequestBody): String {
        val request = Request.Builder()
            .post(requestBody)
            .url(url)
            .headers(headers)
            .build()
        return client.newCall(request).execute().body?.string()!!
    }
}