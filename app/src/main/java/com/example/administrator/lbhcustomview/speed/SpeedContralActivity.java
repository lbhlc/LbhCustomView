package com.example.administrator.lbhcustomview.speed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.speedcontroal.SpeedControlView;
import com.example.lbhlibrary.customview.speedcontroal.SpeedControlViewModify;

import java.util.ArrayList;
import java.util.List;

public class SpeedContralActivity extends AppCompatActivity {

    private SpeedControlViewModify speedControlView;
    private Button speedUp; //油门
    private Button speedDown;//刹车
    private Button shutDown; //手刹
    private List list=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_contral);
        speedControlView = (SpeedControlViewModify) findViewById(R.id.speed_control);

        //实体化
        speedUp = (Button) findViewById(R.id.speed_up);
        speedDown = (Button) findViewById(R.id.speed_down);
        shutDown = (Button) findViewById(R.id.shut_down);
            list.add(30);
            list.add(120);
        list.add(90);
        list.add(160);
        list.add(200);
        list.add(50);
        //设置监听
//        speedUp.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        //按下的时候加速
//                        speedControlView.setType(1);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        //松开做自然减速
//                        speedControlView.setType(0);
//                        break;
//                        default:
//                            break;
//                }
//                return true;
//            }
//        });
        speedUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        for (int i=0;i<list.size();i++)
                        {
                            Log.e("LBH","value="+(Integer) list.get(i));
                            speedControlView.setSpeed((Integer) list.get(i));
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();

            }
        });
        speedDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下的时候减速
                        speedControlView.setType(2);
                        break;
                    case MotionEvent.ACTION_UP:
                        //松开做自然减速
                        speedControlView.setType(0);
                        break;
                        default:
                            break;
                }
                return true;
            }
        });
        shutDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下的时候拉手刹
                        speedControlView.setType(3);
                        break;
                    case MotionEvent.ACTION_UP:
                        //松开做自然减速
                        speedControlView.setType(0);
                        break;
                        default:
                            break;
                }
                return true;
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (speedControlView != null) {
            speedControlView.setSpeed(0);
          //  speedControlView.setStart(true);
        }
//        new Thread(speedControlView).start();



    }

    @Override
    protected void onStop() {
        super.onStop();
        if (speedControlView != null) {
            speedControlView.setSpeed(0);
           // speedControlView.setStart(false);
        }
    }
}
