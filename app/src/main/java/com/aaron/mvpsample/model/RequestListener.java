package com.aaron.mvpsample.model;

public interface RequestListener {

    /**
     * 回调方法
     */
    void onSuccess(String data);

    void onFailed();
}
