package com.thirio.android.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import com.thirio.android.R;
import com.thirio.android.utils.FontChangeCrawler;

public class BMIDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmidisplay);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup) findViewById(R.id.activity_bmi));
        new CountDownTimer(1000, 100) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {


                Intent intent = new Intent(BMIDisplay.this, FoodCuration.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                CardView cardView = (CardView) findViewById(R.id.card_view_bmi);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(BMIDisplay.this, cardView, "transition_image");
                // start the new activity
                startActivity(intent, options.toBundle());
            }
        }.start();
    }
}
