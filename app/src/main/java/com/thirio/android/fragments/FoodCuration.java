package com.thirio.android.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.thirio.android.R;
import com.thirio.android.utils.FontChangeCrawler;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link FoodCuration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodCuration extends Fragment {
    public static final int BREAKFAST = 0;
    public static final int LUNCH = 1;
    public static final int SNACKS = 2;
    public static final int DINNER = 3;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView lunch, breakfast, dinner, snacks;
    MaterialSpinner spinner;
    LinearLayout llTab;
    int selected = 0;
    View v;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoodCuration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodCuration.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodCuration newInstance(String param1, String param2) {
        FoodCuration fragment = new FoodCuration();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_food_curation, container, false);
        llTab = v.findViewById(R.id.lltabs);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        System.out.println("yo " + width);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(width / 5, 0, width / 5, 0);
        llTab.setLayoutParams(layoutParams);
        breakfast = v.findViewById(R.id.breakfast);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getActivity().getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup) v);
        lunch = v.findViewById(R.id.lunch);
        snacks = v.findViewById(R.id.snacks);
        dinner = v.findViewById(R.id.dinner);
        createOnClickListeners();
        select(BREAKFAST);
        spinner = v.findViewById(R.id.spinner);
        spinner.setItems("Main Course", "Appetizers", "Sweet");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, item + " selected", Snackbar.LENGTH_LONG).show();
            }
        });
        return v;

    }

    public void createOnClickListeners() {
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(BREAKFAST);
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(LUNCH);
            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(DINNER);
            }
        });
        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(SNACKS);
            }
        });
    }

    public void initializeSel() {
        breakfast.setBackground(getActivity().getDrawable(R.drawable.leftroundedcorner));
        lunch.setBackground(getActivity().getDrawable(R.drawable.nocorners));
        snacks.setBackground(getActivity().getDrawable(R.drawable.nocorners));
        dinner.setBackground(getActivity().getDrawable(R.drawable.rightroundedcorner));

        breakfast.setTextColor(Color.BLACK);
        lunch.setTextColor(Color.BLACK);
        snacks.setTextColor(Color.BLACK);
        dinner.setTextColor(Color.BLACK);

    }

    public void select(int pos) {
        selected = pos;
        initializeSel();
        switch (selected) {
            case BREAKFAST:
                breakfast.setTextColor(Color.WHITE);
                breakfast.setBackground(getActivity().getDrawable(R.drawable.leftroundedcornerselected));
                break;
            case LUNCH:
                lunch.setTextColor(Color.WHITE);
                lunch.setBackground(getActivity().getDrawable(R.drawable.nocornersselected));
                break;
            case SNACKS:
                snacks.setTextColor(Color.WHITE);
                snacks.setBackground(getActivity().getDrawable(R.drawable.nocornersselected));
                break;
            case DINNER:
                dinner.setTextColor(Color.WHITE);
                dinner.setBackground(getActivity().getDrawable(R.drawable.rightroundedcornerselected));
                break;
        }
    }

}
