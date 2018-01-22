package com.thirio.android.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.thirio.android.R;
import com.thirio.android.adapter.OrdersAdapter;
import com.thirio.android.database.DbMethods;
import com.thirio.android.model.Order;
import com.thirio.android.utils.FontChangeCrawler;

import java.util.List;

public class ConfirmOrder extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    public OrdersAdapter mAdapter;
    public List<Order> mDataset;
    CardView pay, orderMore;
    public static int PAY = 1;
    public static int MORE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_orders);
        WindowManager.LayoutParams params = getWindow().getAttributes();

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup) findViewById(R.id.rootConfirm));
        params.height = (7 * getWindowManager().getDefaultDisplay().getHeight()) / 8;
        params.width = (7 * getWindowManager().getDefaultDisplay().getWidth()) / 8;
        this.getWindow().setAttributes(params);

        StaggeredGridLayoutManager gridlayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridlayoutManager);
        setData();
        pay = (CardView) findViewById(R.id.pay);
        orderMore = (CardView) findViewById(R.id.moreOrders);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmOrder.this, SamplePaymentPortal.class);

                startActivityForResult(intent, PAY);
            }
        });
        orderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    setResult(2);
                    finish();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PAY){
            //Payment success
            if(resultCode==1){
                setResult(1);
                finish();

            }
            //failure
            else if(resultCode==0){
                setResult(0);
                finish();

            }
        }
    }




    public void setData(){
        DbMethods dbMethods=new DbMethods(this);

        mDataset=dbMethods.getAllOrders();
        mAdapter=new OrdersAdapter(mDataset,getBaseContext());
        mRecyclerView.setAdapter(mAdapter);


    }
}
