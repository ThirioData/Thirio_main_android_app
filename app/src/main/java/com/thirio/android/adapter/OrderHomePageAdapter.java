package com.thirio.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thirio.android.R;
import com.thirio.android.model.Order;
import com.thirio.android.utils.FontChangeCrawler;

import java.util.List;

/**
 * Created by abhinav on 22/1/18.
 */


public class OrderHomePageAdapter extends RecyclerView.Adapter<OrderHomePageAdapter.ViewHolder> {
    Context context;
    private List<Order> mDataset;

    public OrderHomePageAdapter(List<Order> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    public void add(int position, Order item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Order item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public OrderHomePageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        OrderHomePageAdapter.ViewHolder vh = null;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_orders, parent, false);
        vh = new OrderHomePageAdapter.ViewHolder(v);

        FontChangeCrawler fontChanger = new FontChangeCrawler(context.getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup)v);
        return vh;
    }

    @Override
    public void onBindViewHolder(OrderHomePageAdapter.ViewHolder holder, final int position) {

        holder.name.setText(mDataset.get(position).getName());
        int d=mDataset.get(position).getDiet();
        Log.d("DIET ",d+"");
        String diet="";
        if(d==1){
            diet="BREAKFAST";
        }
        else if(d==2){
            diet="LUNCH";
        }
        else if(d==3){
            diet="SNACKS";
        }
        else{
            diet="DINNER";
        }
        holder.diet.setText(diet);
        holder.maincourse.setText(mDataset.get(position).getMainCourse());
        holder.salads.setText(mDataset.get(position).getSalads());
        holder.breads.setText(mDataset.get(position).getBreads());
        holder.name.setText(mDataset.get(position).getName());
        holder.sides.setText(mDataset.get(position).getSides());

    }

    @Override
    public int getItemCount() {
        if(mDataset!=null)
        return mDataset.size();
        else return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,diet,maincourse,breads,salads,sides;
        public ViewHolder(View v) {
            super(v);

            name = v.findViewById(R.id.name);
            diet=v.findViewById(R.id.diet);
            maincourse=v.findViewById(R.id.mainCourse);
            breads=v.findViewById(R.id.breads);
            salads=v.findViewById(R.id.salads);
            sides=v.findViewById(R.id.sides);
        }
    }

}
