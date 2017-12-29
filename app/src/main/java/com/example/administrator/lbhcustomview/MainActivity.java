package com.example.administrator.lbhcustomview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.CATransition.TransitionActivity;
import com.example.administrator.lbhcustomview.InstrumentPanel.InstrumentPanelActivity;
import com.example.administrator.lbhcustomview.auditprogress.AuditProgressActivity;
import com.example.administrator.lbhcustomview.bomb.BombActivity;
import com.example.administrator.lbhcustomview.dragviewactivity.DragMainActivity;
import com.example.administrator.lbhcustomview.dropindicator.DropindicatorActivity;
import com.example.administrator.lbhcustomview.passwordview.PassWordActivity;
import com.example.administrator.lbhcustomview.speed.SpeedContralActivity;
import com.example.administrator.lbhcustomview.temperature.TemperatureActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 跳转到仿进度页面
     */
    private Button mAuditProgressActivity;
    /**
     * 跳转炸弹人页面
     */
    private Button mBombActivity;
    /**
     * 跳转太阳页面
     */
    private Button mWeatherActivity;
    /**
     * 仪表盘页面
     */
    private Button mSpeedControlActivity;
    /**
     * 可拖拽图片页面
     */
    private Button mDragviewActivity;
    /**
     * 可拖转场动画页面
     */
    private Button mTransitionActivity;
    /**
     * 跳转到自定义仪表盘
     */
    private Button mInstrumentpanelActivity;
    /**
     * 自定义密码输入框
     */
    private Button mPasswordviewActivity;
    /**
     * 温度计自定义view
     */
    private Button mTemperatureviewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mAuditProgressActivity = (Button) findViewById(R.id.auditProgressActivity);
        mAuditProgressActivity.setOnClickListener(this);
        mBombActivity = (Button) findViewById(R.id.BombActivity);
        mBombActivity.setOnClickListener(this);
        mWeatherActivity = (Button) findViewById(R.id.WeatherActivity);
        mWeatherActivity.setOnClickListener(this);
        mSpeedControlActivity = (Button) findViewById(R.id.speed_control_activity);
        mSpeedControlActivity.setOnClickListener(this);
        mDragviewActivity = (Button) findViewById(R.id.dragview_activity);
        mDragviewActivity.setOnClickListener(this);
        mTransitionActivity = (Button) findViewById(R.id.transition_activity);
        mTransitionActivity.setOnClickListener(this);
        mInstrumentpanelActivity = (Button) findViewById(R.id.instrumentpanel_activity);
        mInstrumentpanelActivity.setOnClickListener(this);
        mPasswordviewActivity = (Button) findViewById(R.id.passwordview_activity);
        mPasswordviewActivity.setOnClickListener(this);
        mTemperatureviewActivity = (Button) findViewById(R.id.temperatureview_activity);
        mTemperatureviewActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.auditProgressActivity:
                startActivity(new Intent(this, AuditProgressActivity.class));
                break;
            case R.id.BombActivity:
                startActivity(new Intent(this, BombActivity.class));
                break;
            case R.id.WeatherActivity:
                startActivity(new Intent(this, DropindicatorActivity.class));
                break;

            case R.id.speed_control_activity:
                startActivity(new Intent(this, SpeedContralActivity.class));
                break;

            case R.id.dragview_activity:
                startActivity(new Intent(this, DragMainActivity.class));
                break;
            case R.id.transition_activity:
                startActivity(new Intent(this, TransitionActivity.class));
                break;
            case R.id.instrumentpanel_activity:
                startActivity(new Intent(this, InstrumentPanelActivity.class));
                break;
            case R.id.passwordview_activity:
                startActivity(new Intent(this, PassWordActivity.class));
                break;
            case R.id.temperatureview_activity:
                startActivity(new Intent(this, TemperatureActivity.class));
                break;
        }
    }
}
