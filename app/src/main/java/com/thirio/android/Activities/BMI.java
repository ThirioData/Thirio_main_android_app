package com.thirio.android.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.roger.gifloadinglibrary.GifLoadingView;
import com.thirio.android.R;
import com.thirio.android.utils.FontChangeCrawler;

import java.text.DecimalFormat;

/**
 * Created by abhinav on 18/12/17.
 */

public class BMI extends AppCompatActivity {
    AnimationDrawable animationDrawable;
    TextView wt, ht;
    TextInputEditText ft, in, kg, age;
    TextView proceed, bmi, cal;
    MaterialSpinner spinner;
    int spinnerVal = 0;
    private GifLoadingView mGifLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.x = -20;
        params.height = (9 * getWindowManager().getDefaultDisplay().getHeight()) / 10;
        params.width = (9 * getWindowManager().getDefaultDisplay().getWidth()) / 10;
//        params.y = -10;

//        this.getWindow().setAttributes(params);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bmi);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toolbar.setLogo(R.drawable.thiriologotext);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup) findViewById(R.id.activity_bmi));
        wt = (TextView) findViewById(R.id.weightTextView);
        ht = (TextView) findViewById(R.id.heightTextView);
//        ft = (TextInputLayout) findViewById(R.id.ftTextInputLayout);
//        in = (TextInputLayout) findViewById(R.id.inTextInputLayout);
//        kg = (TextInputLayout) findViewById(R.id.weightTextInput);
//        age=(TextInputLayout)findViewById(R.id.age)
        ft = (TextInputEditText) findViewById(R.id.ftEditText);
        in = (TextInputEditText) findViewById(R.id.inEditText);
        kg = (TextInputEditText) findViewById(R.id.weightEditText);
        age = (TextInputEditText) findViewById(R.id.ageEditText);
        bmi = (TextView) findViewById(R.id.textViewBMI);
        cal = (TextView) findViewById(R.id.textViewCal);
        proceed = (TextView) findViewById(R.id.proceed);
        proceed.setVisibility(View.GONE);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI.this, FoodCuration.class);

                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(BMI.this, findViewById(R.id.card_view_bmi), "transition_image");
                startActivity(intent, options.toBundle());

            }
        });
        spinner = (MaterialSpinner) findViewById(R.id.spinnerSex);
        spinner.setItems("Male", "Female");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                spinnerVal = position;
                isDone();
            }
        });
        ImageView rocketImage = (ImageView) findViewById(R.id.iconThirio);
        rocketImage.setBackgroundResource(R.drawable.loadingicon);
        animationDrawable = (AnimationDrawable) rocketImage.getBackground();
        animationDrawable.start();
        intializeWatchers();

//        mGifLoadingView = new GifLoadingView();
//        findViewById(R.id.calculate).setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        mGifLoadingView.setImageResource(R.drawable.machine1);
//                        mGifLoadingView.show(getFragmentManager(), "");
//                        mGifLoadingView.setCancelable(false);
//                        new CountDownTimer(2000, 100) {
//
//                            public void onTick(long millisUntilFinished) {
//                            }
//
//                            public void onFinish() {
////                                startActivity(new Intent(BMI.this, MainActivity1.class));
////                                ft.setVisibility(View.GONE);
////                                in.setVisibility(View.GONE);
////                                kg.setVisibility(View.GONE);
//
////                                wt.setText("YOUR BMI IS: 22");
////                                wt.setTextSize(22);
//                                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
////                                wt.setGravity(Gravity.CENTER_HORIZONTAL);
////                                wt.setLayoutParams();
////                                ht.setText("YOU SHOULD TAKE 2000 cal/day");
////                                ht.setTextSize(22);
////                                ht.setGravity(Gravity.CENTER_HORIZONTAL);
////                                findViewById(R.id.calculate).setVisibility(View.GONE);
//                                proceed.setVisibility(View.VISIBLE);
//                                findViewById(R.id.card_view_bmi).setVisibility(View.VISIBLE);
//                                mGifLoadingView.dismiss();
////                                finish();
////                                startActivity(new Intent(BMI.this,BMIDisplay.class));
//
//                            }
//                        }.start();
//
//                    }
//                });
    }

    void intializeWatchers() {
        ft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isDone();
            }
        });
        in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isDone();
            }
        });

        kg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isDone();
            }
        });

        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isDone();
            }
        });
    }

    void isDone() {
        if (!age.getText().toString().isEmpty() && !ft.getText().toString().isEmpty() && !in.getText().toString().isEmpty() && !kg.getText().toString().isEmpty()) {
            proceed.setVisibility(View.VISIBLE);
            calcBMI();
        }
//        re/turn false;
    }

    void calcBMI() {
        double BMI = (Float.valueOf(kg.getText().toString()) / ((Float.valueOf(ft.getText().toString()) + Float.valueOf(in.getText().toString()) / 12) * .3048));
        bmi.setText("BMI " + new DecimalFormat("##.##").format(BMI));
        double cal1 = 10 * Integer.valueOf(kg.getText().toString()) + 6.25 * ((Float.valueOf(ft.getText().toString()) + Float.valueOf(in.getText().toString()) / 12)) * 30.48 - 5 * Integer.valueOf(age.getText().toString());

        if (spinnerVal == 0) {
            cal1 = cal1 + 5;

        } else {
            cal1 = cal1 - 161;
        }
        cal.setText("CALORIE/DAY " + new DecimalFormat("####").format(cal1));

    }
}
