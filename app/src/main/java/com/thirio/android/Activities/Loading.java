package com.thirio.android.Activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import com.thirio.android.R;

public class Loading extends AppCompatActivity {
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_loading);

        ImageView rocketImage = (ImageView) findViewById(R.id.loadingAnimationImageView);
        rocketImage.setBackgroundResource(R.drawable.loadingicon);
        animationDrawable = (AnimationDrawable) rocketImage.getBackground();
        animationDrawable.start();
        new CountDownTimer(1000, 100) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                startActivity(new Intent(Loading.this, MainActivity1.class));
            }
        }.start();
    }
}
