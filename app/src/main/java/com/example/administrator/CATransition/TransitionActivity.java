package com.example.administrator.CATransition;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.administrator.CATransition.manual_transition.AnimeActivity;
import com.example.administrator.CATransition.ui_trasition.ImageActivity;
import com.example.administrator.lbhcustomview.R;

/**
 * 转场动画开始页面
 */
public class TransitionActivity extends AppCompatActivity {
    private Button mAutoTransitionBtn;
    private Button mManualTransitionBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mAutoTransitionBtn = (Button) findViewById(R.id.ui_transition_btn);
        mManualTransitionBtn = (Button) findViewById(R.id.manual_transition_btn);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            mAutoTransitionBtn.setVisibility(View.GONE);
        }
        mAutoTransitionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransitionActivity.this, ImageActivity.class));
            }
        });

        mManualTransitionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransitionActivity.this, AnimeActivity.class));
            }
        });
    }
}
