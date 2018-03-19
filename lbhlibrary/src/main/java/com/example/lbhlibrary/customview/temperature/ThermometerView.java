package com.example.lbhlibrary.customview.temperature;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2018/3/16.
 *
 */

/**
 * 自定义温度计view
 */

public class ThermometerView extends View {
    /**
     * 定义矩形画笔
     */
    private Paint paint;

    /**
     *
     * 矩形
     */
    private Rect rect;

    /**
     *
     * 定义圆弧画笔
     */
    private Paint ciclePaint;

    /**
     *
     * 定义圆弧轮廓
     */
    private RectF rectF;

    /**
     *
     * 定义刻度线画笔
     */
    private Paint linePaint;

    /**
     *
     * 进度
     */
    private float mProgress;
    public ThermometerView(Context context) {
        super(context);
    }

    public ThermometerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initCiclePaint();
        initLinePaint();
        initFilledRectangle();

    }


    public ThermometerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initView() {
        paint=new Paint();
        //设置画笔颜色
        paint.setColor(Color.BLUE);
        //设置画笔抗锯齿
        paint.setAntiAlias(true);
        //让画出的图形是空心的(不填充)
        paint.setStyle(Paint.Style.STROKE);
        rect=new Rect(200,0,300,550);

    }

    /**
     * 定义实心矩形画笔
     */
    private Paint fRPaint;
    private Rect fRect;
    private void initFilledRectangle()
    {
        fRPaint=new Paint();
        fRPaint.setColor(Color.RED);
        fRPaint.setAntiAlias(true);
        fRPaint.setStyle(Paint.Style.FILL);

    }

    /**
     * 画实心矩形方法
     * @param canvas 画笔
     */
    private void drawFRect(Canvas canvas)
    {
        canvas.save();
        canvas.drawRect(fRect,fRPaint);
        canvas.restore();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int len=Math.min(height,width);
        setMeasuredDimension(len,len);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            drawRect(canvas);
            drawArc(canvas);
            drawLine(canvas);
            drawFRect(canvas);
            canvas.save();
            canvas.drawLine(200, 400-mProgress, 300, 400-mProgress, linePaint);
            Log.e("LBH","mprogressY="+(400-mProgress));
            canvas.restore();


    }

    /**
     * 温度计下方的圆弧
     * @param canvas 画笔
     */

    private void drawArc(Canvas canvas) {
        canvas.save();
        canvas.drawArc(rectF,180,-180,true,ciclePaint);
        canvas.restore();
    }

    /**
     * 画矩形
     * @param canvas 画笔
     */
    private void drawRect(Canvas canvas) {
        canvas.save();
        canvas.drawRect(rect,paint);
        canvas.restore();
    }

    /**
     * 定义直线画笔
     */
    private void initLinePaint() {
        linePaint=new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
    }
    /**
     * 画线
     * 画直线时候注意保持开始的y轴值和结束点的y轴值保持一致
     */
    private void drawLine(Canvas canvas)
    {
        canvas.save();
        for (int i=1;i<=110;i++)
        {
            if (i%5==0) {

                canvas.drawLine(300, i * 5, 350, i * 5, linePaint);
                canvas.drawText(((110-i)*2)-60+"",350,i*5,linePaint);
            }else
            {
                canvas.drawLine(300, i * 5, 320, i * 5, linePaint);
            }
        }
        canvas.restore();
    }
    /**
     * 定义圆弧画笔
     */
    private void initCiclePaint() {
        rectF=new RectF(200,500,300,600);
        ciclePaint=new Paint();
        //设置画笔颜色
        ciclePaint.setColor(Color.RED);
        //设置画笔抗锯齿
        ciclePaint.setAntiAlias(true);
        //让画出的图形是实心的(填充)
        ciclePaint.setStyle(Paint.Style.FILL);
    }
   private int frY;
    private void setProgress(float progress) {
        this.mProgress = progress;
        postInvalidate();
        frY= (int) (400-this.mProgress);
        fRect=new Rect(200,frY,300,550);
    }
    float value=0;
    /**
     *
     *
     * @param progress 进度
     */
    public void start(float progress) {
        ValueAnimator valueAnimator= ValueAnimator.ofFloat(progress);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                value= (float) valueAnimator.getAnimatedValue();
                Log.e("LBH","value="+value);
                new Thread()
                {
                    @Override
                    public void run() {
                        setProgress(value*25);
                    }
                }.start();

            }
        });
        valueAnimator.start();

    }


}
