package com.example.lbhlibrary.customview.temperature;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2018/3/16.
 */

/**
 * 温度计view
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
        if (mProgress!=0f)
        {
            canvas.drawLine(200,mProgress,300,mProgress,ciclePaint);
        }
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

    private void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
    }

    /**
     * 存在的问题，目前无法将越过的区域变色
     * 进度条不准确
     * @param progress 进度
     */
    public void start(float progress) {
        setProgress(progress+280);
        AnimatorSet animation = new AnimatorSet();
        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(this, "progress", 0f, mProgress);
        progressAnimation.setDuration(3000);// 动画执行时间
        progressAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.playTogether(progressAnimation);//动画同时执行,可以做多个动画
        animation.start();
    }
}
