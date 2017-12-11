package com.thirio.android.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.thirio.android.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);
        final View textView1=findViewById(R.id.food);
        final View textView2=findViewById(R.id.fitness);
        final View textView3=findViewById(R.id.health);
        final View textView4=findViewById(R.id.indian);
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);
        textView4.setVisibility(View.GONE);
        textView1.animate()
//                .translationY(-1000)
                .alpha(0.0f).setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        //textView1.setVisibility(View.GONE);
                        textView2.setVisibility(View.VISIBLE);
                        textView2.animate()
//                                .translationY(-900)
                                .alpha(0.0f).setDuration(300)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        //textView1.setVisibility(View.GONE);
                                        textView3.setVisibility(View.VISIBLE);
                                        textView3.animate()
//                                                .translationY(-800)
                                                .alpha(0.0f).setDuration(300)
                                                .setListener(new AnimatorListenerAdapter() {
                                                    @Override
                                                    public void onAnimationEnd(Animator animation) {
                                                        super.onAnimationEnd(animation);
                                                        textView4.setVisibility(View.VISIBLE);
                                                        textView4.animate()
//                                                                .translationY(-700)
                                                                .alpha(0.0f).setDuration(300)
                                                                .setListener(new AnimatorListenerAdapter() {
                                                                    @Override
                                                                    public void onAnimationEnd(Animator animation) {
                                                                        super.onAnimationEnd(animation);
                                                                        startActivity(new Intent(Splash.this,Loading.class));
                                                                    }
                                                                });
                                                    }
                                                });
                                    }
                                });


                    }
                });
    }
}
