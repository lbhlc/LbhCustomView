package com.example.administrator.lbhcustomview.InstrumentPanel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.panel.InstrumentPanel;
import com.example.lbhlibrary.customview.panel.PanelValue;

public class InstrumentPanelActivity extends AppCompatActivity implements View.OnClickListener {

    private InstrumentPanel instrumentPanel;
    private PanelValue values;
    private InstrumentPanel mInstrumentpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_panel);
        initView();
    }

    private void initView() {
        instrumentPanel = findViewById(R.id.instrumentpanel);
        mInstrumentpanel = (InstrumentPanel) findViewById(R.id.instrumentpanel);
        mInstrumentpanel.setOnClickListener(this);
        values=instrumentPanel;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.instrumentpanel:
                if (values!=null)
                {
                    values.getValue(200);
                    Log.e("LBH","gogogo");
                }
                break;
        }
    }



}
