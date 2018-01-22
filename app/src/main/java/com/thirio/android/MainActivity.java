package com.thirio.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.felix.bottomnavygation.BottomNav;
import com.felix.bottomnavygation.ItemNav;
import com.thirio.android.fragments.FoodCuration;
import com.thirio.android.fragments.HomePage.MyOrders;
import com.thirio.android.fragments.HomePage.Users;
import com.thirio.android.fragments.foodCuration.Breakfast;
import com.thirio.android.utils.SimpleGestureFilter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SimpleGestureFilter.SimpleGestureListener {
    //    @BindView(R.id.viewPager)
//    ViewPager viewPager;
//    @BindView(R.id.back)
    Fragment home;
    ViewGroup back;
    LinearLayout llBottom1;
    private SimpleGestureFilter detector;
//    private LinearLayout pager_indicator;
//    private int dotsCount;
//    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        setupWindowAnimations();
//        TravelViewPagerAdapter adapter = new TravelViewPagerAdapter(getSupportFragmentManager());
//        setupref();
        detector = new SimpleGestureFilter(this, this);

        home = null;
//        setupNavigationView();
        llBottom1 = (LinearLayout) findViewById(R.id.llcart);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        llBottom1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height / 3));

        setupviews();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.thiriologotext);

//        adapter.addAll(generateTravelList());
//        viewPager.setAdapter(adapter);


//        ExpandingPagerFactory.setupViewPager(viewPager);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                ExpandingFragment expandingFragment = ExpandingPagerFactory.getCurrentFragment(viewPager);
//                if(expandingFragment != null && expandingFragment.isOpenend()){
//                    expandingFragment.close();
//                }
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//                for (int i = 0; i < dotsCount; i++) {
//                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
//                }
//
//                dots[position % 4].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
//
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {

            case SimpleGestureFilter.SWIPE_RIGHT:
                str = "Swipe Right";
                break;
            case SimpleGestureFilter.SWIPE_LEFT:
                str = "Swipe Left";
                break;
            case SimpleGestureFilter.SWIPE_DOWN:
                str = "Swipe Down";

                llBottom1.setVisibility(View.GONE);
                break;
            case SimpleGestureFilter.SWIPE_UP:
                str = "Swipe Up";
                llBottom1.setVisibility(View.VISIBLE);
                break;

        }
//        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {

        List fragmentList = getSupportFragmentManager().getFragments();

        boolean handled = false;
        int i = fragmentList.size();
        int k = 0;
        for (k = 0; k < i; k++) {
            Fragment f = (Fragment) fragmentList.get(k);
            if (f instanceof HomeFragment) {
                handled = ((HomeFragment) f).onBackPressed();

                if (handled) {
                    break;
                }
            }
        }

        if (!handled) {
            super.onBackPressed();
        }
    }

    @Override
    public void onDoubleTap() {

//        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }

    //    public void setupref() {
//
//        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
//        setUiPageViewController();
//
//    }
//    @Override
//    public void onBackPressed() {

//        Snackbar.make(findViewById(R.id.back), "Press back again to exit", Snackbar.LENGTH_LONG).show();

//            super.onBackPressed();
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private void setupWindowAnimations() {
//        Explode slideTransition = new Explode();
//        getWindow().setReenterTransition(slideTransition);
//        getWindow().setExitTransition(slideTransition);
//    }

//    private List<Travel> generateTravelList(){
//        List<Travel> travels = new ArrayList<>();
//        travels.add(new Travel("Seychelles", R.drawable.b, "Seychelles", R.drawable.cupicon));
//        travels.add(new Travel("Shang Hai", R.drawable.c, "Shang Hai", R.drawable.icons));
//        travels.add(new Travel("New York", R.drawable.d, "New York", R.drawable.icon));
//        travels.add(new Travel("castle", R.drawable.e, "castle", R.drawable.stars));
//
//        return travels;
//    }
//    @SuppressWarnings("unchecked")
//    private void startInfoActivity1(View view, Travel travel, int position) {
//        Activity activity = this;
////        startActivity(new Intent(activity,InfoActivityFitness.class));
//
//
//        switch (position) {
//            case 0:
//
//                ActivityCompat.startActivity(activity,
//                        InfoActivityFond.newInstance(activity, travel),
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(
//                                activity,
//                                new Pair<>(view, getString(R.string.transition_image)))
//                                .toBundle());
//                break;
//            case 1:
//
//                ActivityCompat.startActivity(activity,
//                        InfoActivityFood.newInstance(activity, travel),
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(
//                                activity,
//                                new Pair<>(view, getString(R.string.transition_image)))
//                                .toBundle());
//                break;
//            case 2:
//
//                ActivityCompat.startActivity(activity,
//                        InfoActivityFitness.newInstance(activity, travel),
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(
//                                activity,
//                                new Pair<>(view, getString(R.string.transition_image)))
//                                .toBundle());
//                break;
//            default:
//                System.out.println("default case");
//                ActivityCompat.startActivity(activity,
//                        InfoActivity.newInstance(activity, travel),
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(
//                                activity,
//                                new Pair<>(view, getString(R.string.transition_image)))
//                                .toBundle());
//                break;
//        }
//    }
//    @Override
//    public void onExpandingClick(View v) {
//        //v is expandingfragment layout
//        View view = v.findViewById(R.id.image);
//        Travel travel = generateTravelList().get(viewPager.getCurrentItem() % 4);
//        int position = viewPager.getCurrentItem() % 4;
//        startInfoActivity1(view, travel, position);
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//    private void setUiPageViewController() {
//
//        dotsCount = 4;
//        dots = new ImageView[dotsCount];
//
//        for (int i = 0; i < dotsCount; i++) {
//            dots[i] = new ImageView(this);
//            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
//
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//            );
//
//            params.setMargins(4, 0, 4, 0);
//
//            pager_indicator.addView(dots[i], params);
//        }
//
//        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
//    }


    public void setupviews() {
        BottomNav bottomNav = (BottomNav) findViewById(R.id.bottomNav);
        bottomNav.addItemNav(new ItemNav(this, R.drawable.feed, R.drawable.feed_sel).addColorAtive(R.color.colorExtra).addColorInative(android.R.color.white));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.explore, R.drawable.explore_sel).addColorAtive(R.color.colorExtra).addColorInative(android.R.color.white));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.atividades, R.drawable.atividades_sel).addColorAtive(R.color.colorExtra).addColorInative(android.R.color.white));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.perfil, R.drawable.perfil_sel).addColorAtive(R.color.colorExtra).addColorInative(android.R.color.white).isProfileItem().addProfileColorAtive(android.R.color.holo_red_dark).addProfileColorInative(android.R.color.black));
        final BottomNav one = bottomNav;
        BottomNav.OnTabSelectedListener listener = new BottomNav.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
//                Toast.makeText(MainActivity.this, "Click position " + position, Toast.LENGTH_SHORT).show();
                selectFragment1(position);
            }

            @Override
            public void onTabLongSelected(int position) {
//                Toast.makeText(MainActivity.this, "Long position " + position, Toast.LENGTH_SHORT).show();
            }
        };
        bottomNav.setTabSelectedListener(listener);

        bottomNav.build();
        bottomNav.selectTab(0);

    }


    private void setupNavigationView() {
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
//        if (bottomNavigationView != null) {
//
//            // Select first menu item by default and show Fragment accordingly.
//            Menu menu = bottomNavigationView.getMenu();
//            selectFragment(menu.getItem(0));
//
//            // Set action to perform when any menu-item is selected.
//            bottomNavigationView.setOnNavigationItemSelectedListener(
//                    new BottomNavigationView.OnNavigationItemSelectedListener() {
//                        @Override
//                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                            selectFragment(item);
//                            return false;
//                        }
//                    });
//        }
    }

    protected void selectFragment1(int pos) {

//        item.setChecked(true);

        switch (pos) {
            case 0:
                // Action to perform when Home Menu item is selected.
                home = new HomeFragment();
                pushFragment(home);
                break;
            case 2:
                // Action to perform when Bag Menu item is selected.
                pushFragment(new MyOrders());
                break;
            case 3:
                // Action to perform when Account Menu item is selected.
                pushFragment(new Users());
                break;
        }
    }
    /**
     * Perform action when any item is selected.
     *
     * @param item Item that is selected.
     */
    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.action_home:
                home = new HomeFragment();
                pushFragment(home);
                break;
            case R.id.action_fb:
                pushFragment(new FoodCuration());
                break;
            case R.id.action_account:
                pushFragment(new Breakfast());
                break;
        }
    }

    /**
     * Method to push any fragment into given id.
     *
     * @param fragment An instance of Fragment to show into the given id.
     */
    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }
}
