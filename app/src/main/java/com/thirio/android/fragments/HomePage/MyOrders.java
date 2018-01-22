package com.thirio.android.fragments.HomePage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thirio.android.R;
import com.thirio.android.adapter.OrderHomePageAdapter;
import com.thirio.android.database.DbMethods;
import com.thirio.android.model.Order;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrders extends Fragment {

    private RecyclerView mRecyclerView;
    public OrderHomePageAdapter mAdapter;
    public List<Order> mDataset;

    public MyOrders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my_orders, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_my_orders);

        StaggeredGridLayoutManager gridlayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridlayoutManager);
        setData();
        return v;
    }

    public void setData(){
        DbMethods dbMethods=new DbMethods(getContext());

        mDataset=dbMethods.getAllOrders();
        mAdapter=new OrderHomePageAdapter(mDataset,getContext());
        mRecyclerView.setAdapter(mAdapter);


    }
}
