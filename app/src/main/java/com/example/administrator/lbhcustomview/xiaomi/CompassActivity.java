package com.example.administrator.lbhcustomview.xiaomi;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.xiaomi.ChaosCompassView;

/**
 * 自定义指南针页面
 */
public class CompassActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener;
    private ChaosCompassView chaosCompassView;
    private float val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        chaosCompassView = (ChaosCompassView) findViewById(R.id.ccv);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                val = event.values[0];
                chaosCompassView.setVal(val);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        mSensorManager.registerListener(mSensorEventListener,mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(mSensorEventListener);
    }
}
