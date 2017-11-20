package com.example.administrator.lbhcustomview.auditprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.administrator.lbhcustomview.R;
import com.example.lbhlibrary.customview.auditprogress.AuditProgressView;

/**
 * 自定义仿京东退款进度页面
 */
public class AuditProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_progress);
        LinearLayout content = findViewById(R.id.ll_audit_content);

        content.addView(createView(5, true, true, true, false, "提交申请"));
        content.addView(createView(5, true, true, false, false, "审查"));
        content.addView(createView(5, true, true, false, false, "审核"));
        content.addView(createView(5, true, false, false, false, "退款"));
        content.addView(createView(5, false, false, false, false, "完成"));
        content.addView(createView(5, false, false, false, true, "关闭"));
    }
    public AuditProgressView createView(int stepCount, boolean isCurrentComplete, boolean isNextComplete, boolean isFirstStep, boolean isLastStep, String text) {
        AuditProgressView view = new AuditProgressView(this);
        view.setStepCount(stepCount);
        view.setIsCurrentComplete(isCurrentComplete);
        view.setIsNextComplete(isNextComplete);
        view.setIsFirstStep(isFirstStep);
        view.setIsLastStep(isLastStep);
        view.setText(text);
        return view;
    }
}
