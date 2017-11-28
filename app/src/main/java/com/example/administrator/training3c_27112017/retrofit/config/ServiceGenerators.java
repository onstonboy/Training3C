package com.example.administrator.training3c_27112017.retrofit.config;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 11/28/17.
 */

public class ServiceGenerators {

    public static final long TIMEOUT_CONNECTION = TimeUnit.MINUTES.toMillis(1);

    public static GitHubApi createApiService(Context context) {
        Retrofit retrofit = createRetrofit(context);
        return retrofit.create(GitHubApi.class);
    }

    private static Retrofit createRetrofit(Context context) {
        //Gson rule
        Gson gson =
                new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();

        //init okhttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);

        builder.cache(cache);
        builder.readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);
        builder.writeTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);
        builder.connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);

        OkHttpClient okHttpClient = builder.build();

        return new Retrofit.Builder().baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // init rxjava 2
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }
}
