package com.example.administrator.lbhcustomview.thermometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.temperature.ThermometerView;

public class ThermometerActivity extends AppCompatActivity {
  private ThermometerView thermometerView;
  private TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);
        thermometerView=(ThermometerView)findViewById(R.id.thermommeter);
        thermometerView.start(-60f/14f);
        try {
            view.setText("hello");
        }catch (NullPointerException e)
        {
            Log.e("LBH","message="+e.getMessage());
        }
    }
}
