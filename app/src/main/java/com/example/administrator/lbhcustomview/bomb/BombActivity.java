package com.example.administrator.lbhcustomview.bomb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.animation.bomb.BombView;

public class BombActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomb);
        final BombView bombView= (BombView) this.findViewById(R.id.bomb_view);
        bombView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bombView.startAnim();
            }
        });
    }
}
