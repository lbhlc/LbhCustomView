package com.example.lbhlibrary.customview.humidity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author libohan
 * 邮箱:76681287@qq.com
 * create on 2018/3/16.
 */

public class Humidity extends View{
    /**
     * 圆弧半径
     */
    private int radius;
    /**
     * 实例化矩形
     */
    private RectF oval;
    /**
     * 起始角度
     */
    private float startAngle=120;
    /**
     * 扫过角度
     */
    private float sweepAngle=300;
    /**
     * 是否绘制割圆
     */
    private boolean useCenter=false;
    /**
     * 定义圆环画笔
     */
    private Paint paint;
    /**
     *
     * 目标角度
     */
    private float targetAngle=270;
    /**
     * 判断是否在动
     */
    private boolean isRunning;
    /**
     * 判断是回退的状态还是前进状态
     */
    private int state = 1;
    public Humidity(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint =new Paint();
        //设置画笔颜色
        paint.setColor(Color.WHITE);
        //设置画笔抗锯齿
        paint.setAntiAlias(true);
        //让画出的图形是空心的(不填充)
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽度
        int width=MeasureSpec.getSize(widthMeasureSpec);
        //获取高度
        int height=MeasureSpec.getSize(heightMeasureSpec);
        //获得高度和宽度之间的最小值
        int len=Math.min(width,height);
        //实例化矩形
        oval=new RectF(0,0,len,len);
        //将尺度设置成高度和宽度中的最小值
        setMeasuredDimension(len,len);
        radius=len/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //介绍一下绘制圆弧的方法：
//
//        参数一oval是一个RectF对象为一个矩形
//
//         参数二startAngle为圆弧的起始角度
//
//        参数三sweepAngle为圆弧的经过角度（扫过角度）
//
//        参数四useCenter为圆弧是一个boolean值，为true时画的是圆弧，为false时画的是割弧
//
//        参数五paint为一个画笔对象
//        也就是说只要确定了一个矩形，在确定他起始和经过的角度就能够画出一个圆弧（这点大家可以用画板测试）
        canvas.drawArc(oval, startAngle, sweepAngle, useCenter,paint);
    }

    /**
     * 画直线的方法，注意，canvas.save和canvas.restore要成对出现
     * @param canvas 画笔
     */
    private void drawViewLine(Canvas canvas)
    {
        //先保存之前canvas的内容
        canvas.save();
        //移动canvas(X轴移动距离，Y轴移动距离)
        canvas.translate(radius,radius);
//        //旋转坐标系
//        canvas.rotate(30);
        Paint linePatin=new Paint();
        //设置画笔颜色
        linePatin.setColor(Color.WHITE);
        //线宽
        linePatin.setStrokeWidth(2);
        //设置画笔抗锯齿
        linePatin.setAntiAlias(true);

        //确定每次旋转的角度
        float rotateAngle=sweepAngle/100;
        //绘制有色部分的画笔
        Paint targetLinePatin=new Paint();
        targetLinePatin.setColor(Color.GREEN);
        targetLinePatin.setStrokeWidth(2);
        targetLinePatin.setAntiAlias(true);
        //记录已经绘制过的有色部分范围
        float hasDraw=0;
        //画100条刻度线需要101次
        for(int i=0;i<=100;i++){
           if (hasDraw<=targetAngle&&targetAngle!=0) {
               //绘制有色刻度线
               canvas.drawLine(0,radius,0,radius-40,targetLinePatin);
           }else
           {
               //画一条刻度线
               canvas.drawLine(0,radius,0,radius-40,linePatin);
           }
           hasDraw+=rotateAngle;
            //旋转的角度
            canvas.rotate(rotateAngle);
        }
//        //画一条刻度线
//        canvas.drawLine(0,radius,0,radius-40,linePatin);
        //操作完成后恢复状态
        canvas.restore();
    }

    public void changeAngle(final float trueAngle) {
        if (isRunning){//如果在动直接返回
            return;
        }
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                switch (state) {
                    case 1://后退状态
                        isRunning=true;
                        targetAngle -= 3;
                        if (targetAngle <= 0) {//如果回退到0
                            targetAngle = 0;
                            //改为前进状态
                            state = 2;
                        }
                        break;
                    case 2://前进状态
                        targetAngle += 3;
                        if (targetAngle >= trueAngle) {//如果增加到指定角度
                            targetAngle = trueAngle;
                            //改为后退状态
                            state = 1;
                            isRunning=false;
                            //结束本次运动
                            timer.cancel();
                        }
                        break;
                    default:
                        break;
                }
                //重新绘制（子线程中使用的方法）
                postInvalidate();
            }
        }, 500, 30);
    }
}
