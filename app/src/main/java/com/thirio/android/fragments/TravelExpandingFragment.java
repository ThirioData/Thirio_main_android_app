package com.thirio.android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.View;

import com.thirio.android.InfoActivity;
import com.thirio.android.InfoActivityFitness;
import com.thirio.android.InfoActivityFond;
import com.thirio.android.InfoActivityFood;
import com.thirio.android.R;
import com.thirio.android.model.Travel;
import com.thirio.android.utils.ExpandingFragment;
/**
 * this is control fragment , Top and Bottom is child in it.
 *
 * Created by florentchampigny on 21/06/2016.
 */
public class TravelExpandingFragment extends ExpandingFragment {

    static final String ARG_TRAVEL = "ARG_TRAVEL";
    Travel travel;
    public static TravelExpandingFragment newInstance(Travel travel){
        TravelExpandingFragment fragment = new TravelExpandingFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TRAVEL, travel);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            travel = args.getParcelable(ARG_TRAVEL);
        }
    }

    /**
     * include TopFragment
     * @return
     */
    @Override
    public Fragment getFragmentTop() {
        return FragmentTop.newInstance(travel);
    }

    /**
     * include BottomFragment
     * @return
     */
    @Override
    public Fragment getFragmentBottom() {
        return FragmentBottom.newInstance(travel);
    }

    @Override
    protected void OnClick1(View view) {
        Activity activity = getActivity();

        if (travel.getName() == "Seychelles") {

            ActivityCompat.startActivity(activity,
                    InfoActivityFond.newInstance(activity, travel),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            activity,
                            new Pair<>(view, getString(R.string.transition_image)))
                            .toBundle());
        } else if (travel.getName() == "Shang Hai") {

            ActivityCompat.startActivity(activity,
                    InfoActivityFood.newInstance(activity, travel),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            activity,
                            new Pair<>(view, getString(R.string.transition_image)))
                            .toBundle());
        } else if (travel.getName() == "New York") {

            ActivityCompat.startActivity(activity,
                    InfoActivityFitness.newInstance(activity, travel),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            activity,
                            new Pair<>(view, getString(R.string.transition_image)))
                            .toBundle());
        } else {

            System.out.println("default case");
            ActivityCompat.startActivity(activity,
                    InfoActivity.newInstance(activity, travel),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            activity,
                            new Pair<>(view, getString(R.string.transition_image)))
                            .toBundle());
        }
//
    }

    //    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        if (f instanceof OnExpandingClickListener) {
//            mListener = (OnExpandingClickListener) f;
//        } else {
//            throw new RuntimeException(f.toString()
//                    + "ExpandingFragment must implement OnExpandingClickListener");
//        }
//    }
//
}

