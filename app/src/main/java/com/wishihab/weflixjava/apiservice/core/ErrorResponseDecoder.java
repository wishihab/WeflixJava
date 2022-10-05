package com.wishihab.weflixjava.apiservice.core;


import android.app.Application;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.wishihab.weflixjava.model.core.ErrorMeta;
import com.wishihab.weflixjava.model.core.ErrorResponse;


import java.io.EOFException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/** Decode error message from http response, if available.
 *
 * @author Bangun K
 */
public class ErrorResponseDecoder {


    private Gson gson;
    private Application context;
    private Map<String,String> codeMap;

    /** Create error message decoder instance.
     *
     * @param context
     *      Use application context to prevent activity context leak.
     * @param gson
     * @param codeMap
     *      Code map translating from error code to error message.
     */
    public ErrorResponseDecoder(Application context, Gson gson, Map<String,String> codeMap) {
        this.gson = gson;
        this.context = context;
        this.codeMap = codeMap;
    }

    public ErrorResponseDecoder(Application context, Map<String,String> codeMap) {
        this(context, null, codeMap);
    }

    /** Get appropriate message from retrofit exception.
     *  Must be used from inside {@link retrofit2.Callback#onFailure(Call, Throwable)}.
     *  Must NOT used to get message from common exception.
     *
     * @param throwable
     * @return
     */
    public String getMessageFromRetrofitException(Throwable throwable) {


        String message;
        try {
            throw throwable;
        } catch (MalformedJsonException|JsonParseException e) {
            message = "Kembalian server tidak dimengerti, coba beberapa saat lagi";
        } catch (NullPointerException e) {
            message = "Tidak dapat memproses permintaan mu";
        }catch (SocketTimeoutException e) {
            message = "Terjadi masalah koneksi atau server sibuk";
        } catch (EOFException e) {
            // incomplete or malformed content (EOF while parsing)
            // may also occurs when response is completely empty
            message = "Kembalian server tidak dimengerti, coba beberapa saat lagi";
        } catch (IOException e) {
            message = "Masalah koneksi silahkan coba beberapa saat lagi";
        } catch (Throwable e) {
            message = "Kesalahan tidak dapat diketahui";
        }
        return message;
    }

    @Nullable
    public <T> T asObject(Response<?> response, Class<T> cls) {
        T result;
        try {
            result = getGson().fromJson(response.errorBody().charStream(), cls);
        } catch (NullPointerException|JsonParseException e) {
            result = null;
        }
        return result;
    }

    /** Get error message based on response body.
     *
     * @param response
     *      Reponse to decode.
     * @return
     */
    public String getErrorMessage(Response<?> response) {

        ErrorResponse error = decode(response);
        String message = null;
        String code = null;
        String userMessage;


        if (error != null && error.getErrorData() != null) {
            message = error.getErrorData().getMessage();
            code = error.getErrorData().getCode();
        }

        // Get from code map if available
        userMessage = codeMap.get(code);

        // if not available in code, get from message field
        if (userMessage == null) {
            userMessage = message;
        }

        // if not availabe, maybe backend use message on the root of respons
        if (userMessage == null && error != null && error.getMessage() != null &&
                error.getMessage().trim().length() > 0) {
            userMessage = error.getMessage();
        }

        // otherwise just use generic error message (with http code)
        //
        if (userMessage == null) {
            userMessage = "Maaf, terjadi kesalahan. Silakan coba beberapa saat lagi " + response.code();
        }

        return userMessage;
    }

    public String getErrorCode(Response<?> response) {

        ErrorResponse error = decode(response);
        String message = null;
        String code = null;
        String userMessage;


        if (error != null && error.getErrorData() != null) {
            message = error.getErrorData().getMessage();
            code = error.getErrorData().getCode();
        }

        // Get from code map if available
        userMessage = codeMap.get(code);

        // if not available in code, get from message field
        if (userMessage == null) {
            userMessage = code;
        }

        // otherwise just use generic error message (with http code)
        //
        if (userMessage == null) {
            userMessage = "Maaf, terjadi kesalahan. Silakan coba beberapa saat lagi " + response.code();
        }

        return userMessage;
    }


    public String getErrorMetaMessage(Response<?> response) {

        ErrorResponse error = decode(response);
        String message = null;
        String code = null;
        String userMessage;


        if (error != null && error.getErrorMeta() != null) {
            message = error.getErrorMeta().getMessage();
            code = error.getErrorMeta().getCode();
        }

        // Get from code map if available
        userMessage = codeMap.get(code);

        // if not available in code, get from message field
        if (userMessage == null) {
            userMessage = message;
        }

        // otherwise just use generic error message (with http code)
        //
        if (userMessage == null) {
            userMessage = "Maaf, terjadi kesalahan. Silakan coba beberapa saat lagi " + response.code();
        }

        return userMessage;
    }

    public ErrorMeta getErrorMeta(Response<?> response) {

        ErrorResponse error = decode(response);
        String message = null;
        String code = null;
        String userMessage;


        if (error != null && error.getErrorMeta() != null) {
            message = error.getErrorMeta().getMessage();
            code = error.getErrorMeta().getCode();
        }

        // Get from code map if available
        userMessage = codeMap.get(code);

        // if not available in code, get from message field
        if (userMessage == null) {
            userMessage = message;
        }

        // otherwise just use generic error message (with http code)
        //
        if (userMessage == null) {
            userMessage = "Maaf, terjadi kesalahan. Silakan coba beberapa saat lagi " + response.code();
        }

        return error.getErrorMeta();
    }

    @Nullable
    private ErrorResponse decode(Response<?> response) {
        ErrorResponse error;
        try {
            error = getGson().fromJson(response.errorBody().charStream(), ErrorResponse.class);
        } catch (JsonParseException e) {
            error = null;
        }
        return error;
    }

    private Gson getGson() {
        if (gson == null) {
            gson = GsonFactory.createGson();
        }
        return gson;
    }
}
