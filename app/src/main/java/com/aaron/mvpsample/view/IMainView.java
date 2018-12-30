package com.aaron.mvpsample.view;

public interface IMainView {

    void showProgress();

    void hideProgress();

    void load(String data);

    String getInput();

    void clearInput();
}
