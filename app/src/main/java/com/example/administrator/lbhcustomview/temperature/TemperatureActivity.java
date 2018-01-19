package com.example.administrator.lbhcustomview.temperature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.temperature.TemperatureView;

public class TemperatureActivity extends AppCompatActivity {
    private TemperatureView temperatureView;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        temperatureView = (TemperatureView) findViewById(R.id.temperatureView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        temperatureView.start(40);
       // seekBar.setProgress(80 + 40);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                temperatureView.start(progress );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
