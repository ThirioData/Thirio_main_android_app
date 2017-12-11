package com.thirio.android.Activities;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.thirio.android.R;
import com.thirio.android.fragments.foodCuration.Breakfast;
import com.thirio.android.fragments.foodCuration.Dinner;
import com.thirio.android.fragments.foodCuration.Lunch;
import com.thirio.android.fragments.foodCuration.Snacks;

import java.util.ArrayList;
import java.util.List;

public class FoodCuration extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_curation);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        tabLayout.setSelectedTabIndicatorHeight(0);
        tabLayout.setBackgroundColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.BLACK,Color.parseColor("#FF7300"));
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xFF616261,0xFF131313});
        gd.setCornerRadius(0f);
        tabLayout.setBackgroundDrawable(gd);
    }

    private void setupTabIcons() {

        tabLayout.getTabAt(0).setText("B'Fast");

        tabLayout.getTabAt(1).setText("Lunch");
        tabLayout.getTabAt(2).setText("Snacks");
        tabLayout.getTabAt(3).setText("Dinner");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        adapter.addFragment(new Breakfast(), "ONE");
        adapter.addFragment(new Lunch(), "TWO");
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