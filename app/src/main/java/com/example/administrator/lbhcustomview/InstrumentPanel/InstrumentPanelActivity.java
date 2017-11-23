package com.example.administrator.lbhcustomview.InstrumentPanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.panel.InstrumentPanel;

public class InstrumentPanelActivity extends AppCompatActivity {

    private InstrumentPanel instrumentPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_panel);
        initView();
    }

    private void initView() {
        instrumentPanel=findViewById(R.id.instrumentpanel);
    }
}
