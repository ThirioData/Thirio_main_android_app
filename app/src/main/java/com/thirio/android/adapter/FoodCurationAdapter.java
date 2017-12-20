package com.thirio.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thirio.android.R;
import com.thirio.android.model.FoodItem;

import java.util.List;

/**
 * Created by abhinav on 25/12/17.
 */
public class FoodCurationAdapter extends RecyclerView.Adapter<FoodCurationAdapter.ViewHolder> {
    //    private final int SINGLE = 0, DOUBLE = 1;
    Context context;
    private List<FoodItem> mDataset;

    public FoodCurationAdapter(List<FoodItem> myDataset) {
        mDataset = myDataset;
    }

    public void add(int position, FoodItem item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(FoodItem item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public FoodCurationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        ViewHolder vh = null;
//        System.out.println("jhg" + mDataset.get(viewType).getType() + "," + viewType);
//        if (viewType == SINGLE) {
        //        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem_front, parent, false);
        vh = new ViewHolder(v);

//        v.setLayoutParams(lp);
        return vh;
//        } else {
//            View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_sponsors_small, parent, false);
//            vh = new ViewHolder(v1);
//            return vh;
//        }
//        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        if (mDataset.get(position).getType() == 1) {
        holder.name.setText(mDataset.get(position).getName());
        holder.cusine.setText(mDataset.get(position).getCusine());
        holder.desc.setText(mDataset.get(position).getDescription());
//            ImageLoader imageLoader = AppController.getInstance().getImageLoader();

//        imageLoader.get(mDataset.get(position).getImage(), ImageLoader.getImageListener(
//                holder.image, R.drawable.thiriologo, R.drawable.thiriologo));

//            System.out.println("img url " + mDataset.get(position).getUrlimg());
        Glide.with(holder.image.getContext()).load(mDataset.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);
//        } else {
//            holder.text.setText(mDataset.get(position).getSponsorName());
//            System.out.println("img url " + mDataset.get(position).getUrlimg());
//            Glide.with(holder.imageView.getContext()).load(mDataset.get(position).getUrlimg())
//                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
//            holder.text1.setText(mDataset.get(position).getSponsorName1());
//            Glide.with(holder.imageView1.getContext()).load(mDataset.get(position).getUrlimg1())
//                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView1);
//
//        }

    }

    @Override
    public int getItemCount() {

        return mDataset.size();
//        else return 0;
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (mDataset.get(position).getType() == 1) {
//            return SINGLE;
//        } else {
//            return DOUBLE;
//        }
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView image;
        public TextView desc;
        public TextView cusine;

        public ViewHolder(View v) {
            super(v);
//            int pos = getAdapterPosition();
//            if(mDataset.get(pos).getType()==1) {
            name = v.findViewById(R.id.name);
            image = v.findViewById(R.id.foodpic);
//            }
//            else{
//                text = (TextView) v.findViewById(R.id.textinformal);
//                imageView = (ImageView) v.findViewById(R.id.imginformal);
            desc = v.findViewById(R.id.description);
            cusine = v.findViewById(R.id.cusine);

//            }


//            text.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    System.out.println("position "+position);
//
//                    System.out.println("link "+mDataset.get(position).getHyperlinkimg());
////                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mDataset.get(position).getHyperlinkimg()));
////                    imageView.getContext().startActivity(browserIntent);
//                }
//            });
        }
    }


}