package com.thirio.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.thirio.android.adapter.TravelViewPagerAdapter;
import com.thirio.android.model.Travel;
import com.thirio.android.utils.ExpandingFragment;
import com.thirio.android.utils.ExpandingPagerFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ExpandingFragment.OnExpandingClickListener, View.OnClickListener {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.back)
    ViewGroup back;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupWindowAnimations();
        TravelViewPagerAdapter adapter = new TravelViewPagerAdapter(getSupportFragmentManager());
        setupref();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.thiriologotext);
        adapter.addAll(generateTravelList());
        viewPager.setAdapter(adapter);


        ExpandingPagerFactory.setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ExpandingFragment expandingFragment = ExpandingPagerFactory.getCurrentFragment(viewPager);
                if(expandingFragment != null && expandingFragment.isOpenend()){
                    expandingFragment.close();
                }

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                }

                dots[position % 4].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setupref() {

        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        setUiPageViewController();

    }
    @Override
    public void onBackPressed() {
        if(!ExpandingPagerFactory.onBackPressed(viewPager)){
            super.onBackPressed();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Explode slideTransition = new Explode();
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    private List<Travel> generateTravelList(){
        List<Travel> travels = new ArrayList<>();
        travels.add(new Travel("Seychelles", R.drawable.b, "Seychelles", R.drawable.cupicon));
        travels.add(new Travel("Shang Hai", R.drawable.c, "Shang Hai", R.drawable.icons));
        travels.add(new Travel("New York", R.drawable.d, "New York", R.drawable.icon));
        travels.add(new Travel("castle", R.drawable.e, "castle", R.drawable.stars));

        return travels;
    }
    @SuppressWarnings("unchecked")
    private void startInfoActivity1(View view, Travel travel, int position) {
        Activity activity = this;
//        startActivity(new Intent(activity,InfoActivityFitness.class));


        switch (position) {
            case 0:

                ActivityCompat.startActivity(activity,
                        InfoActivityFond.newInstance(activity, travel),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                activity,
                                new Pair<>(view, getString(R.string.transition_image)))
                                .toBundle());
                break;
            case 1:

                ActivityCompat.startActivity(activity,
                        InfoActivityFood.newInstance(activity, travel),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                activity,
                                new Pair<>(view, getString(R.string.transition_image)))
                                .toBundle());
                break;
            case 2:

                ActivityCompat.startActivity(activity,
                        InfoActivityFitness.newInstance(activity, travel),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                activity,
                                new Pair<>(view, getString(R.string.transition_image)))
                                .toBundle());
                break;
            default:
                System.out.println("default case");
                ActivityCompat.startActivity(activity,
                        InfoActivity.newInstance(activity, travel),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                activity,
                                new Pair<>(view, getString(R.string.transition_image)))
                                .toBundle());
                break;
        }
    }
    @Override
    public void onExpandingClick(View v) {
        //v is expandingfragment layout
        View view = v.findViewById(R.id.image);
        Travel travel = generateTravelList().get(viewPager.getCurrentItem() % 4);
        int position = viewPager.getCurrentItem() % 4;
        startInfoActivity1(view, travel, position);
    }

    @Override
    public void onClick(View v) {

    }

    private void setUiPageViewController() {

        dotsCount = 4;
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }
}
