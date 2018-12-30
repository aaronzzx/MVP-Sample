package com.aaron.mvpsample.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class MainModel implements IMainModel {

    @Override
    public void save(final Context context, final String data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = context.getSharedPreferences
                        ("data", Context.MODE_PRIVATE).edit();
                editor.putString("input", data);
                editor.apply();
            }
        }).start();
    }

    @Override
    public void load(final Context context, final RequestListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clearData(context);
                SharedPreferences preferences = context.getSharedPreferences
                        ("data", Context.MODE_PRIVATE);
                String get = preferences.getString("input", null);
                if (!TextUtils.isEmpty(get)) {
                    listener.onSuccess(get);
                } else {
                    listener.onFailed();
                }
            }
        }).start();
    }

    @Override
    public void clearData(final Context context) {
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor = context.getSharedPreferences
                ("data", Context.MODE_PRIVATE).edit();
        editor.clear();
    }
}
