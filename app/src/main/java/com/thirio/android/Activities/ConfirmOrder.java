package com.thirio.android.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_orders);
        WindowManager.LayoutParams params = getWindow().getAttributes();

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup) findViewById(R.id.rootConfirm));
        params.height = ( 7*getWindowManager().getDefaultDisplay().getHeight()) / 8;
        params.width = ( 7*getWindowManager().getDefaultDisplay().getWidth()) / 8;
        this.getWindow().setAttributes(params);

        StaggeredGridLayoutManager gridlayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridlayoutManager);
        setData();
    }
    public void setData(){
        DbMethods dbMethods=new DbMethods(this);

        mDataset=dbMethods.getAllOrders();
        mAdapter=new OrdersAdapter(mDataset,getBaseContext());
        mRecyclerView.setAdapter(mAdapter);


    }
}
