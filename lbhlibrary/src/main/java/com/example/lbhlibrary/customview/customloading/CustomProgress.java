package com.example.lbhlibrary.customview.customloading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lbhlibrary.R;


/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/7.
 */

/**
 * 仿ios的loading框
 */
public class CustomProgress extends Dialog {
    public CustomProgress(@NonNull Context context) {
        super(context);
    }

    public CustomProgress(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = (ImageView) findViewById(R.id.image);
        // 获取ImageView上的动画背景
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        // 开始动画
        spinner.start();
    }
    /**
     * 给Dialog设置提示信息
     *
     * @param message
     */
    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = (TextView) findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
    }
    /**
     * 弹出自定义ProgressDialog
     *
     * @param context
     *            上下文
     * @param message
     *            提示
     * @param cancelable
     *            是否按返回键取消
     * @param cancelListener
     *            按下返回键监听
     * @return
     */
    public static CustomProgress show(Context context, CharSequence message, boolean cancelable, OnCancelListener cancelListener) {
        CustomProgress dialog = new CustomProgress(context,R.style.Custom_Progress);
        dialog.setTitle("");
        dialog.setContentView(R.layout.progress_custom);
        if (message == null || message.length() == 0) {
        dialog.findViewById(R.id.message).setVisibility(View.GONE);
    } else {
        TextView txt = (TextView) dialog.findViewById(R.id.message);
        txt.setText(message);
    }
    // 按返回键是否取消
        dialog.setCancelable(cancelable);
    // 监听返回键处理
        dialog.setOnCancelListener(cancelListener);
    // 设置居中
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
    WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
    // 设置背景层透明度
    lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        return dialog;

    }

}
