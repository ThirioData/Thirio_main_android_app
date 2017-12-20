package com.thirio.android.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thirio.android.R;
import com.thirio.android.model.FoodItem;
import com.thirio.android.utils.FontChangeCrawler;

import java.util.List;

public class FoodCuration extends AppCompatActivity {

    public static final String JSON_URL = "https://quarkbackend.com/getfile/eternaldivine100/thirio";
    public static final int BREAKFAST = 0;
    public static final int LUNCH = 1;
    public static final int SNACKS = 2;
    public static final int DINNER = 3;
    List<FoodItem> mDataset;
    TextView lunch, breakfast, dinner, snacks;
    //    MaterialSpinner spinner;
    LinearLayout llTab;
    int selected = 0;

    //    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.x = -20;
        params.height = (9 * getWindowManager().getDefaultDisplay().getHeight()) / 10;
        params.width = (9 * getWindowManager().getDefaultDisplay().getWidth()) / 10;
//        params.y = -10;

        this.getWindow().setAttributes(params);


        setContentView(R.layout.activity_food_curation);
        llTab = (LinearLayout) findViewById(R.id.lltabs);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        System.out.println("yo " + width);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(width / 8, 0, width / 8, 0);
        llTab.setLayoutParams(layoutParams);
        breakfast = (TextView) findViewById(R.id.breakfast);
//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_food_item);
//
//        GridLayoutManager gridlayoutManager = new GridLayoutManager(getBaseContext(), 1);
//        mRecyclerView.setLayoutManager(gridlayoutManager);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "font/helveticaneuecond.ttf");
        fontChanger.replaceFonts((ViewGroup) findViewById(R.id.foodCurationTop));
        lunch = (TextView) findViewById(R.id.lunch);
        snacks = (TextView) findViewById(R.id.snacks);
        dinner = (TextView) findViewById(R.id.dinner);
        createOnClickListeners();
        select(BREAKFAST);
//        spinner = (MaterialSpinner) findViewById(R.id.spinner);
//        spinner.setItems("Main Course", "Appetizers", "Sweet");
//        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

//            @Override
//            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                Snackbar.make(view, item + " selected", Snackbar.LENGTH_LONG).show();
//            }
//        });
//        sendRequest();
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
        breakfast.setBackground(getDrawable(R.drawable.leftroundedcorner));
        lunch.setBackground(getDrawable(R.drawable.nocorners));
        snacks.setBackground(getDrawable(R.drawable.nocorners));
        dinner.setBackground(getDrawable(R.drawable.rightroundedcorner));

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
                breakfast.setBackground(getDrawable(R.drawable.leftroundedcornerselected));
                break;
            case LUNCH:
                lunch.setTextColor(Color.WHITE);
                lunch.setBackground(getDrawable(R.drawable.nocornersselected));
                break;
            case SNACKS:
                snacks.setTextColor(Color.WHITE);
                snacks.setBackground(getDrawable(R.drawable.nocornersselected));
                break;
            case DINNER:
                dinner.setTextColor(Color.WHITE);
                dinner.setBackground(getDrawable(R.drawable.rightroundedcornerselected));
                break;
        }
    }


//    private void sendRequest() {
//        final ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//
//
//        StringRequest stringRequest = new StringRequest(JSON_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        System.out.println(response);
//                        JsonParseFood pj = new JsonParseFood();
//                        pj.parseJSONteam(response);
//                        mDataset = pj.getData();
//                        mAdapter = new FoodCurationAdapter(mDataset);
//                        mRecyclerView.setAdapter(mAdapter);
//                        pDialog.dismiss();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
////        RequestQueue requestQueue=AppController.getInstance().getRequestQueue();
////// Adding request to request queue
////        requestQueue.add(stringRequest);
//
////        AppController.getInstance().addToRequestQueue(stringRequest);
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//    }

}