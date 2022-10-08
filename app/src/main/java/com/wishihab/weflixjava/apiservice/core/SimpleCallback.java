package com.wishihab.weflixjava.apiservice.core;

import retrofit2.Call;
import retrofit2.Response;

public abstract class SimpleCallback<T> implements retrofit2.Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            handleHttpResult(call, response);
        } catch (NullPointerException e) {
            onFailure(call, e);
        }
    }

    private void handleHttpResult(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onHttpResponseSuccess(call, response);
        } else {
            onHttpResponseFailed(call, response);
        }
    }
    protected abstract void onHttpResponseSuccess(Call<T> call, Response<T> response);

    protected abstract void onHttpResponseFailed(Call<T> call, Response<T> response);
}
