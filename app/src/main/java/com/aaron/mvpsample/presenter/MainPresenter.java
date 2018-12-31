package com.aaron.mvpsample.presenter;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

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
                mHandler.post(() -> {
                    mMainView.hideProgress();
                    mMainView.load(data);
                    mMainView.showToast("加载完成");
                    mMainView.clearInput();
                    mMainModel.clearData(context);
                });
            }

            @Override
            public void onFailed() {
                mHandler.post(() -> {
                    mMainView.hideProgress();
                    mMainView.showToast("查找不到信息,请先存储数据");
                });
            }
        });
    }

    @Override
    public void saveData(final Context context, String data) {
        if (TextUtils.isEmpty(data)) {
            mMainView.showToast("请输入内容后再保存");
            return;
        }
        mMainModel.save(context, data);
        mHandler.post(() -> mMainView.showToast("已保存"));
    }
}
