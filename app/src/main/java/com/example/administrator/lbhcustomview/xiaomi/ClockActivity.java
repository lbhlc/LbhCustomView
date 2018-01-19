package com.example.administrator.lbhcustomview.xiaomi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.xiaomi.BarUtils;

/**
 * 自定义钟表页面
 */
public class ClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        BarUtils.setColor(this,getResources().getColor(R.color.ocbg),0);
    }
}
