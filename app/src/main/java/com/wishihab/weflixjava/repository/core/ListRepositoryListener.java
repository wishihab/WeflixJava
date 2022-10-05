package com.wishihab.weflixjava.repository.core;

import androidx.annotation.NonNull;

import java.util.List;

/** Listener for repository that return list.
 *  It help reduce nested angle bracket compared
 *  to using {@link RepositoryListener}.
 *
 * @param <T>
 */
public interface ListRepositoryListener<T> {

    /**
     * Invoked when repository action success.
     *
     * @param data
     */
    void onSuccess(@NonNull List<T> data);

    /**
     * Invoked when repository action failed.
     *
     * @param message
     */
    void onError(@NonNull String message);
}
