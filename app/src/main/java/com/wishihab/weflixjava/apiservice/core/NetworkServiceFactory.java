package com.wishihab.weflixjava.apiservice.core;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.viewbinding.BuildConfig;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkServiceFactory {


    @NonNull
    public static <T> T createService(
            @NonNull String baseUrl,
            @NonNull final Class<T> clazz) {
        OkHttpClient.Builder builder = createClientBuilder();
        return createRetrofitService(builder, baseUrl, clazz);
    }

    // package private
    static <T> T createRetrofitService(
            @NonNull OkHttpClient.Builder builder,
            @NonNull String baseUrl,
            @NonNull Class<T> clazz) {

        Gson gson = GsonFactory.createGson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(builder.build())
                .build();

        return retrofit.create(clazz);
    }

    // package private
    @NotNull
    static OkHttpClient.Builder createClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // only log on debug build
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.interceptors().add(interceptor);
        }

        return builder;
    }

    @NonNull
    public static <T> T createService(
            Context context,
            @NonNull String baseUrl,
            @NonNull final Class<T> clazz) {
        OkHttpClient.Builder builder = createClientBuilder();
        return createRetrofitService(builder, baseUrl, clazz);
    }

}
