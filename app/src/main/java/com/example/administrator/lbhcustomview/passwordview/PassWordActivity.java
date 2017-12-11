package com.example.administrator.lbhcustomview.passwordview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.passwordview.PswText;

public class PassWordActivity extends AppCompatActivity implements View.OnClickListener, PswText.InputCallBack {
    private final String TAG = "LBH";
    /**
     * CHANGE_MODE
     */
    private Button mBtnChangeMode;
    private PswText mPsw;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word);
        initView();
    }

    private void initView() {

        mBtnChangeMode = (Button) findViewById(R.id.btn_change_mode);
        mBtnChangeMode.setOnClickListener(this);
        mPsw = (PswText) findViewById(R.id.psw);
        mPsw.setInputCallBack(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_change_mode:
                Log.e(TAG,"password=" + password);
                break;
        }
    }


    @Override
    public void onInputFinish(String password) {
        this.password=password;
    }
}
