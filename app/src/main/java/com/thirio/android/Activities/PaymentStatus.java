package com.thirio.android.Activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thirio.android.R;
import com.thirio.android.database.DbMethods;
import com.thirio.android.utils.FontChangeCrawler;

public class PaymentStatus extends AppCompatActivity {
    TextView status,message;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup) findViewById(R.id.card_view_status));
        status=(TextView)findViewById(R.id.paymentStatus);
        message=(TextView)findViewById(R.id.message);
        imageView=(ImageView)findViewById(R.id.imageView);
        Intent i=getIntent();
        int s=i.getIntExtra("STATUS",0);
        if(s==1){
            status.setText("PAYMENT SUCCESSFUL");
            DbMethods dbMethods=new DbMethods(this);
            dbMethods.paymentSuccess();
            dbMethods.deleteAllOrderNotPaid();
            message.setText("You can check your orders for more info");
            imageView.setImageDrawable(getDrawable(R.drawable.success));
            imageView.setImageTintList(ColorStateList.valueOf(Color.GREEN));
            status.setTextColor(Color.GREEN);
        }
        else{
            status.setText("PAYMENT UNSUCCESSFUL");
            message.setText("You can rebuild your order");
            imageView.setImageDrawable(getDrawable(R.drawable.fail));
            imageView.setImageTintList(ColorStateList.valueOf(Color.RED));
            status.setTextColor(Color.RED);
        }
        new CountDownTimer(3000, 100) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                finish();
            }
        }.start();
    }
}
