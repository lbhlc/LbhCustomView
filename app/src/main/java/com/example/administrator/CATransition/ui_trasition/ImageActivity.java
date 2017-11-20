package com.example.administrator.CATransition.ui_trasition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.CATransition.CustomImage;
import com.example.administrator.lbhcustomview.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void imageClick(View view) {
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(this, view, "image_transition");

        CustomImage image = (CustomImage) view;
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_IMAGE, image.getImageId());
        startActivity(intent, options.toBundle());
    }
}
