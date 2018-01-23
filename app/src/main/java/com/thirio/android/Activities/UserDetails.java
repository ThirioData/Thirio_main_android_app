package com.thirio.android.Activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.thirio.android.R;
import com.thirio.android.database.DbMethods;
import com.thirio.android.utils.FontChangeCrawler;

import java.text.DecimalFormat;

public class UserDetails extends AppCompatActivity {
    MaterialSpinner spinner;
    String spinnerVal="Male";
    DbMethods dbMethods;
    AppCompatAutoCompleteTextView name,contact;
    TextView proceed;
    public static int BMI=1;
    public static int CURATION=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.x = -20;
        params.height = (9 * getWindowManager().getDefaultDisplay().getHeight()) / 10;
        params.width = (9 * getWindowManager().getDefaultDisplay().getWidth()) / 10;
//        params.y = -10;

        this.getWindow().setAttributes(params);

        setContentView(R.layout.activity_user_details);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup) findViewById(R.id.rootUser));
        spinner = (MaterialSpinner) findViewById(R.id.spinnerSex);
        spinner.setItems("Male", "Female");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                spinnerVal = item;
            }
        });
        name= (AppCompatAutoCompleteTextView) findViewById(R.id.userName);
        dbMethods=new DbMethods(this);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,dbMethods.getAllUsers());
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,dbMethods.getAllContacts());

        name.setAdapter(adapter);
        contact=(AppCompatAutoCompleteTextView) findViewById(R.id.mobileEditText);
        contact.setAdapter(adapter1);
        ImageView rocketImage = (ImageView) findViewById(R.id.iconThirio);
        rocketImage.setBackgroundResource(R.drawable.loadingicon);
        AnimationDrawable animationDrawable = (AnimationDrawable) rocketImage.getBackground();
        animationDrawable.start();
        proceed=(TextView)findViewById(R.id.next);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty() || contact.getText().toString().isEmpty()){
                    TextInputLayout namelayout,numberlayout;
                    numberlayout=(TextInputLayout)findViewById(R.id.mobileTextInputLayout);
                    namelayout=(TextInputLayout)findViewById(R.id.nameTextInputLayout);
                    if(contact.getText().toString().isEmpty())
                        numberlayout.setError("Enter mobile number");
                    if(name.getText().toString().isEmpty())
                        namelayout.setError("Enter name");

                }
                else if(contact.getText().toString().length()!=10||contact.getText().toString().startsWith("0")||contact.getText().toString().startsWith("1")||contact.getText().toString().startsWith("2")||contact.getText().toString().startsWith("3")||contact.getText().toString().startsWith("4")){
                    TextInputLayout numberlayout;
                    numberlayout=(TextInputLayout)findViewById(R.id.mobileTextInputLayout);
                    numberlayout.setError("Enter correct mobile number");
                }
                else {
                    DbMethods dbMethods=new DbMethods(UserDetails.this);

                    if(dbMethods.ifAlreadyExists(name.getText().toString())==-1){
                    Intent intent = new Intent(UserDetails.this, BMI.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("mobile", contact.getText().toString());
                    intent.putExtra("sex", spinnerVal);
                    startActivityForResult(intent,BMI);}
                    else{
                        int i=dbMethods.ifAlreadyExists(name.getText().toString());
                        Intent food=new Intent(UserDetails.this,FoodCuration.class);
                        int ht=dbMethods.getHeight(name.getText().toString());
                        int wt=dbMethods.getWeight(name.getText().toString());
                        int age=dbMethods.getAge(name.getText().toString());
                        String sex=dbMethods.getSex(name.getText().toString());
                        double cal1= 10 * wt + 6.25 * ht  - 5 * age;

                        Log.d("SEX, AGE, WRIGHT HEIGHT",sex+" "+age+" "+wt+ " "+ht);

                        if (sex.equals("Male")) {
                            cal1 = cal1 + 5;

                        } else {
                            cal1 = cal1 - 161;
                        }
                        food.putExtra("CALORIE",new DecimalFormat("####").format(cal1)+"");
                        food.putExtra("id",i);
                        startActivityForResult(food,CURATION);
                    }
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==BMI || requestCode==CURATION){
            if(resultCode==1){
                Intent intent=new Intent(UserDetails.this,PaymentStatus.class);
                intent.putExtra("STATUS",1);
                startActivity(intent);
                finish();
            }
            else if(resultCode==0){
                Intent intent=new Intent(UserDetails.this,PaymentStatus.class);
                intent.putExtra("STATUS",0);
                startActivity(intent);
                finish();
            }
            else if(resultCode==2){
                finish();
                startActivity(new Intent(UserDetails.this,UserDetails.class));
            }
        }
    }
}
