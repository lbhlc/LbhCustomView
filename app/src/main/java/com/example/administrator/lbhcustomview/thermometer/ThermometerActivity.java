package com.example.administrator.lbhcustomview.thermometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.temperature.ThermometerView;

public class ThermometerActivity extends AppCompatActivity {
  private ThermometerView thermometerView;
  private TextView view;
  private float screenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);

        thermometerView=(ThermometerView)findViewById(R.id.thermommeter);

        try {
            view.setText("hello");
        }catch (NullPointerException e)
        {
            Log.e("LBH","message="+e.getMessage());
        }
    }
    private float initView()
    {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenHeight = displayMetrics.heightPixels;
        screenHeight=screenHeight*16/2750;
      return screenHeight;
    }

    @Override
    protected void onResume() {
        super.onResume();
        float value=initView();
        Log.e("LBH","oncreate value="+value);
        thermometerView.start(116.5f/(2*value));
    }
}
