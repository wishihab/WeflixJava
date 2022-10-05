package com.wishihab.weflixjava.util.core;

/** Consumer functional interface.
 *  Interface for compatibility, because it may not available on lower api level.
 *  While desugaring Java 8 API available on gradle plugin >= 4.0.1
 *  at the time of writing this they are still buggy.
 */
public interface Consumer<T> {
    void accept(T data);
}
