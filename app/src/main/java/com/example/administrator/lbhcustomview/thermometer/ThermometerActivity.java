package com.example.administrator.lbhcustomview.thermometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.temperature.ThermometerView;

import java.util.ArrayList;
import java.util.List;

public class ThermometerActivity extends AppCompatActivity {
  private ThermometerView thermometerView;
  private TextView view;
  private float screenHeight;
  private List<Float>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);
        list.add(110f);
        list.add(-20f);

        thermometerView=(ThermometerView)findViewById(R.id.thermommeter);
        thermometerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thermometerView.start(110/(2*value));
            }
        });

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
   private float value;
    @Override
    protected void onResume() {
        super.onResume();
        value=initView();
        Log.e("LBH","oncreate value="+value);
        for (float v:list)
        {
            thermometerView.start(v/(2*value));
        }

    }
}
