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

        // Historically, default URL retrieved from resources using context.
        // Currently it is not used, and context has been kept for API compatibility
        String url = BaseUrl.URL;
        return createService(url, clazz);
    }

    /**
     * Create service WITHOUT token with default base URL.
     *
     * @param <T>     the type parameter
     * @param context the context
     * @param clazz   the clazz
     * @return the t
     */
    @NonNull
    public static <T> T createDefaultServiceNoToken(@NonNull Context context, @NonNull final Class<T> clazz) {
        return createDefaultService(context, clazz);
    }

    /** Create service WITHOUT any token with given base URL.
     */
    @NonNull
    private static <T> T createServiceNoToken(@NonNull String baseUrl, @NonNull final Class<T> clazz) {
        return createService(baseUrl, clazz);
    }
}
