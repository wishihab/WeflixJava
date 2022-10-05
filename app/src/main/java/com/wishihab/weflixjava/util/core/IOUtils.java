package com.wishihab.weflixjava.util.core;

import androidx.annotation.Nullable;


import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

import kotlin.text.Charsets;

public final class IOUtils {


    /** Compatibility for apache common IO.
     *  New code should use try-with-resources instead.
     *
     * @param closeable
     */
    public static void close(@Nullable Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }

    @Nullable
    public static String asString(@Nullable InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }

        String result;

        char[] chars = new char[128];
        try (Reader reader = new InputStreamReader(inputStream, Charsets.UTF_8)){
            StringBuilder sb = new StringBuilder();
            while (true) {
                int len = reader.read(chars);
                if (len < 0 ) {
                    break;
                }
                sb.append(chars, 0, len);

            }
            result = sb.toString();
        } catch (IOException e) {
            result = null;
        }

        return result;
    }

    public static void copy(InputStream input, OutputStream output) throws IOException {

        String result;

        final int size = 1024;
        byte[] bytes = new byte[size];
        while (true) {
            int len = input.read(bytes);
            if (len < 0) {
                break;
            }
            output.write(bytes, 0, len);
        }
        output.flush();
    }
}
