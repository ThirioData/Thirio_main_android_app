package com.thirio.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thirio.android.R;
import com.thirio.android.model.User;
import com.thirio.android.utils.FontChangeCrawler;

import java.util.List;

/**
 * Created by abhinav on 22/1/18.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    Context context;
    private List<User> mDataset;

    public UsersAdapter(List<User> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    public void add(int position, User item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(User item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        UsersAdapter.ViewHolder vh = null;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_users, parent, false);
        vh = new UsersAdapter.ViewHolder(v);

        FontChangeCrawler fontChanger = new FontChangeCrawler(context.getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup)v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UsersAdapter.ViewHolder holder, int position) {
        holder.name.setText(mDataset.get(position).getName());
        Log.d("USER","bind view holder "+position);
        holder.age.setText(mDataset.get(position).getAge()+" years");
        holder.height.setText(mDataset.get(position).getHeight()+" meters");
        holder.weight.setText(mDataset.get(position).getWeight()+" kgs");

    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            Log.d("SIZE",mDataset.size()+"");
            return mDataset.size();

        } else {
            return 0;

        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,age,height,weight;
        public ViewHolder(View v) {
            super(v);

            name = v.findViewById(R.id.userName);
            age=v.findViewById(R.id.age);
            weight=v.findViewById(R.id.weight);
            height=v.findViewById(R.id.height);

        }
    }

}
