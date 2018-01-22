package com.thirio.android.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thirio.android.JSON.JsonParseFood;
import com.thirio.android.R;
import com.thirio.android.adapter.FoodCurationAdapter;
import com.thirio.android.fragments.SideMenu.MenuFragment;
import com.thirio.android.model.FoodItem;

import java.util.List;

public class Foods extends AppCompatActivity implements FoodCurationAdapter.customButtonListener {
    ViewFlipper viewFlipper;
    public String JSON_URL;
    List<FoodItem> mDataset;
    ProgressBar progressBar;
    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
    public FoodCurationAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        Intent intent=getIntent();
        JSON_URL=intent.getStringExtra("foodURL");
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_food_item);
        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.height = ( 7*getWindowManager().getDefaultDisplay().getHeight()) / 8;
        params.width = ( 7*getWindowManager().getDefaultDisplay().getWidth()) / 8;
        this.getWindow().setAttributes(params);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        int height=params.height;
        int width=params.width;
        CardView c= (CardView)findViewById(R.id.cardFood);
//        c.setLayoutParams(new CardView.LayoutParams(width,height));
        CardView.LayoutParams l=new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.MATCH_PARENT);
//        c.setLayoutParams(l);
        StaggeredGridLayoutManager gridlayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridlayoutManager);
        sendRequest();
        setupMenu();
    }

    @Override
    public void onButtonClickListner(int position) {
        String itemName=mDataset.get(position).getName();
        String itemImage=mDataset.get(position).getImage();
        Intent intent=new Intent();
        intent.putExtra("ITEMNAME",itemName);
        intent.putExtra("ITEMIMAGE",itemImage);

        Toast.makeText(Foods.this, "Selected " + itemName,
                Toast.LENGTH_SHORT).show();
        setResult(1,intent);
        finish();

    }
    private void sendRequest() {
//        final ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();



        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);
                        JsonParseFood pj = new JsonParseFood();
                        pj.parseJSONteam(response);
                        mDataset = pj.getData();
                        mAdapter = new FoodCurationAdapter(mDataset,getBaseContext());
//                        FoodCurationAdapter.customButtonListener(Foods.this);
                        mAdapter.setCustomButtonListner(Foods.this);
                        mRecyclerView.setAdapter(mAdapter);
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        pDialog.dismiss();
                        progressBar.setVisibility(View.GONE);
                    }
                });
//        RequestQueue requestQueue=AppController.getInstance().getRequestQueue();
//// Adding request to request queue
//        requestQueue.add(stringRequest);

//        AppController.getInstance().addToRequestQueue(stringRequest);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuFragment mMenuFragment = (MenuFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }
}
