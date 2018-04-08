package com.example.lbhlibrary.customview.temperature;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;


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
     * 填充矩形
     */
    private Rect rectFill;
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

    /**
     *
     * 右侧坐标
     */
    private   int right;

    /**
     *
     * 屏幕高度取值
     */
    private int heightS;
    /**
     * 自定义view宽度的一半
     */
    private int widthS;

    /**
     *
     * 下圆的半径
     */
    private int radius;

    /**
     *
     * 缓冲矩形的高的差值
     */
    private int heightDifValue;
    /**
     * 中间动态画笔
     */
    private Paint lineMidPaint;

    public ThermometerView(Context context) {
        super(context);
    }

    public ThermometerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getWidthAndHeight();
        initView();
        initCiclePaint();
        initLinePaint();
        initFilledRectangle();
        initFillRect();
        initMidLinePaint();

    }

    /**
     * 获取屏幕高和宽
     */
    private void getWidthAndHeight()
    {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        /*
      屏幕宽度
     */
        int screenWidth = displayMetrics.widthPixels;
        /*
      屏幕高度
     */
        int screenHeight = displayMetrics.heightPixels;
        right= screenWidth /2;
        heightS=4* screenHeight /5;
        widthS= screenWidth /28;
        radius= screenWidth /10;
        heightDifValue= screenWidth /14;
    }
    /**
     * 初始化填充矩形
     */
    private void initFillRect() {
        rectFill=new Rect(right-widthS, (int) (heightS*4/5-((2f)*heightS/550)),right+widthS,heightS*4/5+heightDifValue);
    }


    public ThermometerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 定义矩形画笔
     */
    private void initView() {
        paint=new Paint();
        //设置画笔颜色
        paint.setColor(Color.parseColor("#B0C4DE"));
        //设置画笔抗锯齿
        paint.setAntiAlias(true);
        //让画出的图形是空心的(不填充)
        paint.setStyle(Paint.Style.FILL);
        rect=new Rect(right-widthS,0,right+widthS,heightS*4/5);

    }

    /**
     * 定义实心矩形画笔
     */
    private Paint fRPaint;
    private Rect fRect;

    /**
     * 定义动态矩形画笔
     */
    private void initFilledRectangle()
    {
        fRPaint=new Paint();
       //fRPaint.setColor(Color.parseColor("#D2691E"));
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
        int len=Math.max(height,width);
        setMeasuredDimension(heightS*4/5, len);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            drawRect(canvas);
            drawArc(canvas);
            drawLine(canvas);
            drawFRect(canvas);
            drawFillRect(canvas);
            canvas.save();
            canvas.drawLine(right-widthS, 32*heightS/55-mProgress-((2f)*heightS/550), right+widthS, 32*heightS/55-mProgress-((2f)*heightS/550), lineMidPaint);
            canvas.restore();


    }

    /**
     * 温度计下方的圆弧
     * @param canvas 画笔
     */

    private void drawArc(Canvas canvas) {
        canvas.save();
        canvas.drawArc(rectF,0,360,true,ciclePaint);
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
     * 画填充矩形
     * @param canvas 画笔
     */
    private void drawFillRect(Canvas canvas) {
        canvas.save();
        canvas.drawRect(rectFill,ciclePaint);
        canvas.restore();
    }
    /**
     * 定义直线画笔
     */
    private void initLinePaint() {
        linePaint=new Paint();
        linePaint.setTextSize(30f);
        linePaint.setColor(Color.parseColor("#D2691E"));
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 定义中间动态画笔
     */
    private void initMidLinePaint() {
        lineMidPaint=new Paint();
        lineMidPaint.setColor(Color.parseColor("#FFD700"));
        lineMidPaint.setStrokeWidth(6f);
        lineMidPaint.setAntiAlias(true);
        lineMidPaint.setStyle(Paint.Style.STROKE);
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

                canvas.drawLine(right+widthS, i * (4*heightS/550), right+(2*widthS), i * (4*heightS/550), linePaint);
                canvas.drawText(((110-i)*2)-60+"",right+(2*widthS),i*(4*heightS/550),linePaint);
            }else
            {
                //canvas.drawLine(right+widthS, i * (4*heightS/550), right+widthS+widthS/2, i * (4*heightS/550), linePaint);
                Log.e("LBH","绘制短线的方法");
            }
        }
        canvas.restore();
    }
    /**
     * 定义圆弧画笔
     */
    private void initCiclePaint() {
        rectF=new RectF(right-radius,4*heightS/5+heightDifValue-radius/4,right+radius,4*heightS/5+radius*5/4+heightDifValue);
        ciclePaint=new Paint();
        //设置画笔颜色
        //ciclePaint.setColor(Color.parseColor("#D2691E"));
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
        frY= (int) (32*heightS/55-this.mProgress);
        //动态绘制
        fRect=new Rect(right-widthS,frY,right+widthS,4*heightS/5);
    }
    float value=0;
    /**
     * @param progress 进度
     */
    public void start(float progress) {
        ValueAnimator valueAnimator= ValueAnimator.ofFloat(progress);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                value= (float) valueAnimator.getAnimatedValue();
                //在子线程中绘制保证主线程不会被阻塞
                new Thread()
                {
                    @Override
                    public void run() {
                        setProgress(value*(4*heightS/550)*(4*heightS/550));
                        Log.e("LBH","value1="+value*(4*heightS/550)*(4*heightS/550));
                        Log.e("LBH","value2="+value*49);
                        Log.e("LBH","value3="+(4*heightS/550));
//                        try {
//                            Thread.sleep(4000);
//                            setProgress(300);
//                            Thread.sleep(4000);
//                            setProgress(75);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }.start();
            }
        });
        valueAnimator.start();

    }


}
