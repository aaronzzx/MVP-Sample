package com.aaron.mvpsample.view;

public interface IMainView {

    void showToast(String msg);

    void showProgress();

    void hideProgress();

    void load(String data);

    String getInput();

    void clearInput();
}
