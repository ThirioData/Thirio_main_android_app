package com.thirio.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.thirio.android.fragments.HomePage.TravelExpandingFragment;
import com.thirio.android.model.Travel;
import com.thirio.android.utils.ExpandingViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Qs on 16/5/30.
 */
public class TravelViewPagerAdapter extends ExpandingViewPagerAdapter {

    List<Travel> travels;

    public TravelViewPagerAdapter(FragmentManager fm) {
        super(fm);
        travels = new ArrayList<>();
    }

    public void addAll(List<Travel> travels){
        this.travels.addAll(travels);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Travel travel = travels.get(position % getRealCount());
        return TravelExpandingFragment.newInstance(travel);
    }
    @Override
    public int getCount() {
        if (getRealCount() == 0) {
            return 0;
        }
        // warning: scrolling to very high values (1,000,000+) results in
        // strange drawing behaviour
        return Integer.MAX_VALUE;
    }

    /**
     * @return the {@link #getCount()} result of the wrapped adapter
     */
    public int getRealCount() {
        return travels.size();
    }
//
//    @Override
//    public int getCount() {
//        return travels.size();
//    }

}
