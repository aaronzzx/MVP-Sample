package com.aaron.mvpsample.presenter;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.aaron.mvpsample.model.IMainModel;
import com.aaron.mvpsample.model.MainModel;
import com.aaron.mvpsample.model.RequestListener;
import com.aaron.mvpsample.view.IMainView;

public class MainPresenter implements IMainPresenter {

    private IMainView mMainView;
    private IMainModel mMainModel;
    private Handler mHandler;

    public MainPresenter(IMainView mainView) {
        mMainView = mainView;
        mMainModel = new MainModel();
        mHandler = new Handler();
    }

    @Override
    public void showData(final Context context) {
        mMainView.showProgress();
        mMainModel.load(context, new RequestListener() {
            @Override
            public void onSuccess(final String data) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mMainView.hideProgress();
                        mMainView.load(data);
                        Toast.makeText(context, "加载完成", Toast.LENGTH_SHORT).show();
                        mMainView.clearInput();
                    }
                });
            }

            @Override
            public void onFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mMainView.hideProgress();
                        Toast.makeText(context, "查找不到信息,请先存储数据", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void saveData(final Context context, String data) {
        if (TextUtils.isEmpty(data)) {
            Toast.makeText(context, "请输入内容后再保存", Toast.LENGTH_SHORT).show();
            return;
        }
        mMainModel.save(context, data);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "已保存", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
