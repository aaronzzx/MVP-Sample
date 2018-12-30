package com.aaron.mvpsample.presenter;

import android.content.Context;

public interface IMainPresenter {

    void showData(Context context);

    void saveData(Context context, String data);
}
