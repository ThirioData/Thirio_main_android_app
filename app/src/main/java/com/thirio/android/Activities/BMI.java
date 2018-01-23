package com.thirio.android.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.roger.gifloadinglibrary.GifLoadingView;
import com.thirio.android.R;
import com.thirio.android.database.DbMethods;
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
    double heightInM;
    MaterialSpinner spinner;
    int spinnerVal = 0;
    String name,mobile,sex;
    private GifLoadingView mGifLoadingView;
    double cal1;
    DbMethods dbMethods;
    public static int CURATION=2;

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
                intent.putExtra("CALORIE",new DecimalFormat("####").format(cal1)+"");

                dbMethods=new DbMethods(BMI.this);
                long id;
                id = dbMethods.insertUser(name, sex, heightInM, Integer.parseInt(kg.getText().toString()), Integer.parseInt(age.getText().toString()), mobile);


                    Log.d("value",cal1+"");
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(BMI.this, findViewById(R.id.card_view_bmi), "transition_image");
                intent.putExtra("id",id);
                Bundle bundle=new Bundle();
                bundle.putAll(options.toBundle());
                intent.putExtras(bundle);
                startActivityForResult(intent, CURATION);

            }
        });

      intializeWatchers();
      Intent intent=getIntent();
      name=intent.getStringExtra("name");
      sex=intent.getStringExtra("sex");
      mobile=intent.getStringExtra("mobile");

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
                if(Integer.parseInt(ft.getText().toString())>7){
                    ft.setText("7");
                }
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
                if(Integer.parseInt(in.getText().toString())>11){
                    in.setText("11");
                }
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
                if(Integer.parseInt(kg.getText().toString())>130){
                    kg.setText("130");
                }
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
                if(Integer.parseInt(age.getText().toString())>80){
                    age.setText("80");
                }
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
       heightInM =((Float.valueOf(ft.getText().toString()) + (Float.valueOf(in.getText().toString())) / 12) * .3048);
        double BMI = (Float.valueOf(kg.getText().toString()) /heightInM );
        bmi.setText("BMI " + new DecimalFormat("##.##").format(BMI));
         cal1= 10 * Integer.valueOf(kg.getText().toString()) + 6.25 * heightInM * 100 - 5 * Integer.valueOf(age.getText().toString());

        if (spinnerVal == 0) {
            cal1 = cal1 + 5;

        } else {
            cal1 = cal1 - 161;
        }
        cal.setText("CALORIE/DAY " + new DecimalFormat("####").format(cal1));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CURATION){
            if(resultCode==1){
                //succ in payment
                setResult(1);
                finish();
            }
            if(resultCode==0){
             //fail in payment
                setResult(1);
                finish();
            }
            if (resultCode==2){
                //order more
                setResult(2);
                finish();
            }
        }

    }
}
