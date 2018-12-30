package com.aaron.mvpsample.model;

import android.content.Context;

public interface IMainModel {

    void save(Context context, String data);

    void load(Context context, RequestListener listener);

    void clearData(Context context);
}
