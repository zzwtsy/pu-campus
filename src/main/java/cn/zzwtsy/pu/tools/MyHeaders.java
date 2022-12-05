package cn.zzwtsy.pu.tools;

import okhttp3.Headers;

import static cn.zzwtsy.pu.tools.MyStatic.userConfig;

/**
 * 请求头
 *
 * @author zzwtsy
 * @since 2022/11/30
 */
public class MyHeaders {
    private static final String OAUTH_TOKEN = userConfig.getOauthToken();
    private static final String OAUTH_TOKEN_SECRET = userConfig.getOauthTokenSecret();

    public static Headers baseHeaders() {
        return new Headers.Builder()
                .add("User-Agent", "client:Android version:6.8.71 Product:M2012K11AC OsVersion:12")
                .add("Host", "pocketuni.net")
                .add("Accept-Encoding", "gzip")
                .add("Connection", "Keep-Alive")
                .add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .build();
    }

    public static Headers tokenHeaders() {
        return new Headers.Builder()
                .add("User-Agent", "client:Android version:6.8.71 Product:M2012K11AC OsVersion:12")
                .add("Host", "pocketuni.net")
                .add("Accept-Encoding", "gzip")
                .add("Connection", "Keep-Alive")
                .add("oauth_token", OAUTH_TOKEN)
                .add("oauth_token_secret", OAUTH_TOKEN_SECRET)
                .add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .build();
    }
}
