package com.aaron.mvpsample.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aaron.mvpsample.R;
import com.aaron.mvpsample.presenter.IMainPresenter;
import com.aaron.mvpsample.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IMainView {

    private IMainPresenter mMainPresenter;
    private TextView mData;
    private EditText mInput;
    private Button mSave, mLoad;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(this);
        initView();
        setClickListener();
    }

    private void initView() {
        mData = findViewById(R.id.main_data);
        mInput = findViewById(R.id.main_input);
        mSave = findViewById(R.id.main_save);
        mLoad = findViewById(R.id.main_load);
        mProgress = new ProgressDialog(this);
        mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgress.setTitle("Google Play");
        mProgress.setCancelable(false);
    }

    private void setClickListener() {
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = getInput();
                mMainPresenter.saveData(MainActivity.this, data);
            }
        });
        mLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.showData(MainActivity.this);
            }
        });
    }

    @Override
    public void showProgress() {
        mProgress.show();
    }

    @Override
    public void hideProgress() {
        mProgress.dismiss();
    }

    @Override
    public void load(String data) {
        mData.setText(data);
    }

    @Override
    public String getInput() {
        return mInput.getText().toString();
    }

    @Override
    public void clearInput() {
        mInput.setText("");
    }
}
