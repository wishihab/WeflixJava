package com.wishihab.weflixjava.repository.core;

import androidx.annotation.NonNull;

public interface RepositoryListener<T> {
    /**
     * Invoked when repository action success.
     *
     * @param data
     */
    void onSuccess(@NonNull T data);

    /**
     * Invoked when repository action failed.
     *
     * @param message
     */
    void onError(@NonNull String message);
}
