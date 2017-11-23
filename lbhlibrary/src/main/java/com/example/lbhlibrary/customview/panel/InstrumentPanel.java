package com.example.lbhlibrary.customview.panel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/11/23.
 *         自定义仪表盘
 */

public class InstrumentPanel extends View {
    /**
     * 自定义的长度和宽度
     */
    private int length;
    /**
     * 自定义圆的半径
     */
    private float radius;

    /**
     *
     * 在什么范围内的矩形
     */
    private RectF oval;

    /**
     *
     * 开始角度
     * 扫过的角度
     */
    private float startangle=120;
    private float sweepangle=300;
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 是画割弧还是圆弧
     */
    private boolean useCenter=false;

    /**
     *
     * 目标角度
     */
    private float targetAngle=270;
    public InstrumentPanel(Context context) {
        super(context);
    }

    public InstrumentPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint=new Paint();
        //设置画笔颜色
        paint.setColor(Color.RED);
        //设置画笔抗锯齿
        paint.setAntiAlias(true);
        //设置画笔是否是空心圆,空心圆
        paint.setStyle(Paint.Style.STROKE);
    }

    public InstrumentPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获得实际宽的尺寸
        int width=MeasureSpec.getSize(widthMeasureSpec);
        //获得实际长的尺寸
        int height=MeasureSpec.getSize(heightMeasureSpec);
        //获得宽和高之间较小的长度
        length=Math.min(width,height);
        //获得半径
        radius=length/2;
        //初始化矩形
        oval=new RectF(0,0,length,length);
        //获得一个正方形区域
        setMeasuredDimension(length,length);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(oval,startangle,sweepangle,useCenter,paint);
        drawViewLine(canvas);
    }

    private void drawViewLine(Canvas canvas) {
        canvas.save();
        //将坐标系移动到圆心位置
        Paint linePaint=new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setAntiAlias(true);
        Paint lineTargetPaint=new Paint();
        lineTargetPaint.setColor(Color.RED);
        lineTargetPaint.setAntiAlias(true);
        Paint textPaint=new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        canvas.translate(radius,radius);
//        //将坐标系旋转30度
       canvas.rotate(30);
        float rotateAngle=sweepangle/100;
        float hasDraw=0;
        for (int i=0;i<100;i++) {
            if (hasDraw<=targetAngle&&targetAngle!=0)
            {
                canvas.drawLine(0,radius,0,radius-40,lineTargetPaint);
            }
            if (hasDraw%60==0&&hasDraw!=0)
            {
                //因为开始角度是120，扫过的角度是300度
                canvas.drawLine(0, radius, 0, radius - 80, linePaint);
                canvas.drawText("hello",0,radius-80,textPaint);
            }else {
                canvas.drawLine(0, radius, 0, radius - 40, linePaint);
            }
            hasDraw+=rotateAngle;
            canvas.rotate(rotateAngle);
        }
        //和上面的save方法必须成对出现
        canvas.restore();
    }
}
