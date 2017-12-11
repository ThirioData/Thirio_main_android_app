package com.thirio.android.Activities;

/**
 * Created by abhinav on 9/12/17.
 */

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.thirio.android.R;
import com.thirio.android.fragments.foodCuration.Dinner;
import com.thirio.android.fragments.foodCuration.Snacks;
import com.thirio.android.fragments.landing.FOND;
import com.thirio.android.fragments.landing.FOOD;

import java.util.ArrayList;
import java.util.List;

public class LandingActivity extends AppCompatActivity {
    ViewPagerAdapter adapter;
    private Toolbar toolbar;
//    private TabLayout tabLayout;
    private ViewPager viewPager;
    ValueAnimator mColorAnimation;
    Integer[] colors = {Color.parseColor("#f42f0f"), Color.parseColor("#6d9302"), Color.parseColor("#e10d0a"),Color.parseColor("#ff7300")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        PageIndicatorView pageIndicatorView=(PageIndicatorView)findViewById(R.id.pageIndicatorView);

        pageIndicatorView.setUnselectedColor(Color.parseColor("#99ffffff"));
        pageIndicatorView.setSelectedColor(Color.parseColor("#7040A3"));
        pageIndicatorView.setAnimationDuration(500);
        pageIndicatorView.setAnimationType(AnimationType.SWAP);
        pageIndicatorView.setViewPager(viewPager);
//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        setupTabIcons();
//        tabLayout.setSelectedTabIndicatorHeight(0);
//        tabLayout.setBackgroundColor(Color.WHITE);
//        tabLayout.setTabTextColors(Color.BLACK,Color.parseColor("#FF7300"));
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xFF616261,0xFF131313});
        gd.setCornerRadius(0f);
//        tabLayout.setBackgroundDrawable(gd);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    ArgbEvaluator argbEvaluator=new ArgbEvaluator();
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));

                } else {

                    viewPager.setBackgroundColor(colors[colors.length - 1]);

                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

//    private void setupTabIcons() {
//
//        tabLayout.getTabAt(0).setText("B'Fast");
//
//        tabLayout.getTabAt(1).setText("Lunch");
//        tabLayout.getTabAt(2).setText("Snacks");
//        tabLayout.getTabAt(3).setText("Dinner");
//    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        adapter.addFragment(new FOND(), "ONE");
        adapter.addFragment(new FOOD(), "TWO");
        adapter.addFragment(new Snacks(), "THREE");
        adapter.addFragment(new Dinner(), "FOUR");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
            return null;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.dashboard1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
//            case R.id.action_logout:
//
//                SharedPreferences myprefs=getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor=myprefs.edit();
//                editor.putBoolean(REGISTERED,false);
//                editor.commit();
//                startActivity(new Intent(Dashboard.this,HomeActivity.class));
//                finish();
//                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT)
//                        .show();
//                break;
//            // action with ID action_settings was selected
//            case R.id.action_gallery:
//                startActivity(new Intent(Dashboard.this,GridViewActivity.class));
//                break;
            default:
                break;
        }

        return true;
    }
}