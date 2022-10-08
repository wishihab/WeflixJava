package com.wishihab.weflixjava.apiservice.general;


import static com.wishihab.weflixjava.apiservice.core.NetworkServiceFactory.createService;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wishihab.weflixjava.BaseUrl;


public class WeflixNetworkServiceFactory {

    @NonNull
    public static <T> T createDefaultService(
            @NonNull Context context,
            @NonNull final Class<T> clazz) {

        String url = BaseUrl.URL;
        return createService(url, clazz);
    }
    @NonNull
    public static <T> T createDefaultServiceNoToken(@NonNull Context context, @NonNull final Class<T> clazz) {
        return createDefaultService(context, clazz);
    }

    @NonNull
    private static <T> T createServiceNoToken(@NonNull String baseUrl, @NonNull final Class<T> clazz) {
        return createService(baseUrl, clazz);
    }
}
