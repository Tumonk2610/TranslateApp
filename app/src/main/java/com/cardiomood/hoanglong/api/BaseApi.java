package com.cardiomood.hoanglong.api;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Administrator on 28/07/2015.
 */
public class BaseApi {


    public RestAdapter viewOrClick2() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(100, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(100, TimeUnit.SECONDS);

        return new RestAdapter.Builder()
                .setEndpoint("https://www.google.com")
                .setClient(new OkClient(okHttpClient))
                .build();
    }
}
