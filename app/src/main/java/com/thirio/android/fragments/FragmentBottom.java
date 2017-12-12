package com.thirio.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thirio.android.R;
import com.thirio.android.model.Travel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentBottom extends Fragment {

    static final String ARG_TRAVEL = "ARG_TRAVEL";
    Travel travel;

    @BindView(R.id.descimg)
    ImageView image;
    @BindView(R.id.desc)
    TextView title;

    public static FragmentBottom newInstance(Travel travel) {
        Bundle args = new Bundle();
        FragmentBottom fragmentBottom = new FragmentBottom();
        args.putParcelable(ARG_TRAVEL, travel);
        fragmentBottom.setArguments(args);
        return fragmentBottom;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            travel = args.getParcelable(ARG_TRAVEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (travel != null) {
            image.setImageResource(travel.getImagedesc());
            title.setText(travel.getDesc());
        }

    }

}
