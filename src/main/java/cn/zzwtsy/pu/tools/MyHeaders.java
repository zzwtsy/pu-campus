package cn.zzwtsy.pu.tools;

import okhttp3.Headers;

/**
 * 请求头
 *
 * @author zzwtsy
 * @since 2022/11/30
 */
public class MyHeaders {

    public static Headers baseHeaders() {
        return new Headers.Builder()
                .add("User-Agent", "client:Android version:6.8.71 Product:M2012K11AC OsVersion:12")
                .add("Host", "pocketuni.net")
                .add("Accept-Encoding", "gzip")
                .add("Connection", "Keep-Alive")
                .add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .build();
    }

    public static Headers tokenHeaders(String oauthToken, String oauthTokenSecret) {
        return new Headers.Builder()
                .add("User-Agent", "client:Android version:6.8.71 Product:M2012K11AC OsVersion:12")
                .add("Host", "pocketuni.net")
                .add("Accept-Encoding", "gzip")
                .add("Connection", "Keep-Alive")
                .add("oauth_token", oauthToken)
                .add("oauth_token_secret", oauthTokenSecret)
                .add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .build();
    }
}
