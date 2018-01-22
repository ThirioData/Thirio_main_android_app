package com.thirio.android.fragments.foodCuration;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thirio.android.Activities.ConfirmOrder;
import com.thirio.android.Activities.Foods;
import com.thirio.android.R;
import com.thirio.android.database.DbMethods;
import com.thirio.android.utils.FontChangeCrawler;

import static com.thirio.android.network.URLs.SNACKS_BREADS;
import static com.thirio.android.network.URLs.SNACKS_MAINCOURSE;
import static com.thirio.android.network.URLs.SNACKS_SALADS;
import static com.thirio.android.network.URLs.SNACKS_SIDES;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Snacks.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Snacks#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Snacks extends Fragment{
    public static final int MAINCOURSE=0,SIDES=1,SALADS=2,BREADS=3,CONFIRM=4;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int id;
    String itemNameSalad,itemNameMainCourse,itemNameSides,itemNameBreads;
    CardView sides,maincourse,salads,breads,proceed;
    ImageView sidesImg,maincourseImg,saladsImg,breadsImg;

    private Snacks.OnFragmentInteractionListener mListener;

    public Snacks() {
        // Required empty public constructor
    }

    public static Lunch newInstance(String param1, String param2) {
        Lunch fragment = new Lunch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_snacks, container, false);
        id=getArguments().getInt("id");

        maincourse=v.findViewById(R.id.maincourse);
        salads=v.findViewById(R.id.salad);
        breads=v.findViewById(R.id.breads);
        sides=v.findViewById(R.id.sides);
        proceed=v.findViewById(R.id.proceed);
        maincourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Foods.class);
                intent.putExtra("foodURL",SNACKS_MAINCOURSE);
                startActivityForResult(intent,MAINCOURSE);
            }
        });
        sides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Foods.class);
                intent.putExtra("foodURL",SNACKS_SIDES);
                startActivityForResult(intent,SIDES);
            }
        });
        salads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Foods.class);
                intent.putExtra("foodURL",SNACKS_SALADS);
                startActivityForResult(intent,SALADS);
            }
        });
        breads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Foods.class);
                intent.putExtra("foodURL",SNACKS_BREADS);
                startActivityForResult(intent,BREADS);
            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbMethods dbMethods=new DbMethods(getContext());
                dbMethods.insertOrderNotPaid(id,itemNameMainCourse,itemNameSalad,itemNameSides,itemNameBreads,3);
                Intent intent=new Intent(getContext(), ConfirmOrder.class);
                startActivityForResult(intent,CONFIRM);
            }
        });

        sidesImg=v.findViewById(R.id.snacksImage);
        saladsImg=v.findViewById(R.id.saladImage);
        maincourseImg=v.findViewById(R.id.maincourseImage);
        breadsImg=v.findViewById(R.id.breadsImage);
//        listener.onClick(maincourse);
//        listener.onClick(salads);
//        listener.onClick(breads);
//        listener.onClick(sides);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getActivity().getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup) v);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==MAINCOURSE)
        {
            if(resultCode==1) {
                itemNameMainCourse= data.getStringExtra("ITEMNAME");
                String itemImage = data.getStringExtra("ITEMIMAGE");
                Log.d("TAG", itemImage + itemNameMainCourse);

                Glide.with(getContext()).load(itemImage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(maincourseImg);
                maincourseImg.setVisibility(View.VISIBLE);
            }
        }
        if(requestCode==SIDES)
        {
            if(resultCode==1) {
                itemNameSides= data.getStringExtra("ITEMNAME");
                String itemImage = data.getStringExtra("ITEMIMAGE");
//                Log.d("TAG", itemImage + itemName);

                Glide.with(getContext()).load(itemImage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(sidesImg);
                sidesImg.setVisibility(View.VISIBLE);
            }
        }
        if(requestCode==SALADS)
        {
            if(resultCode==1) {
                itemNameSalad = data.getStringExtra("ITEMNAME");
                String itemImage = data.getStringExtra("ITEMIMAGE");
//                Log.d("TAG", itemImage + itemName);

                Glide.with(getContext()).load(itemImage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(saladsImg);
                saladsImg.setVisibility(View.VISIBLE);
            }
        }
        if(requestCode==BREADS)
        {
            if(resultCode==1) {
                itemNameBreads = data.getStringExtra("ITEMNAME");
                String itemImage = data.getStringExtra("ITEMIMAGE");
//                Log.d("TAG", itemImage + itemName);

                Glide.with(getContext()).load(itemImage).placeholder(R.drawable.loading)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(breadsImg);
                breadsImg.setVisibility(View.VISIBLE);
            }
        }
        if(requestCode==CONFIRM){
            if(resultCode==0){
                //fail
                getActivity().setResult(0);
                getActivity().finish();
            }
            else if(resultCode==1){
                //succ

                getActivity().setResult(1);
                getActivity().finish();
            }
            else if(resultCode==2){
                //order more

                getActivity().setResult(2);
                getActivity().finish();

            }
        }
    }
}