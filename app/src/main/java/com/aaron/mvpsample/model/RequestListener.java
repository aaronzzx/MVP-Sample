package com.aaron.mvpsample.model;

public interface RequestListener {

    void onSuccess(String data);

    void onFailed();
}
