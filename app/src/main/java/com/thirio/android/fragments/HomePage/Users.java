package com.thirio.android.fragments.HomePage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thirio.android.R;
import com.thirio.android.adapter.UsersAdapter;
import com.thirio.android.database.DbMethods;
import com.thirio.android.model.User;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Users extends Fragment {


    private RecyclerView mRecyclerView;
    public UsersAdapter mAdapter;
    public List<User> mDataset;
    public Users() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_users, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_users);

        StaggeredGridLayoutManager gridlayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridlayoutManager);
        setData();
        return v;
    }

    public void setData(){
        DbMethods dbMethods=new DbMethods(getContext());

        mDataset=dbMethods.getAllUserDetails();
        mAdapter=new UsersAdapter(mDataset,getContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


    }
}
