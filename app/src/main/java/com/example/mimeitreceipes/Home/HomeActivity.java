package com.example.mimeitreceipes.Home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mimeitreceipes.BotttomNav.BottomNavigationViewHelper;
import com.example.mimeitreceipes.Image.UniversalImageLoader;
import com.example.mimeitreceipes.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG, "activity_home.xml:started");

        /* << -------------------- set up BottomNavigation -------------------- >> */
        setUpBottomNavigation();


        /* << -------------------- set up ViewPager -------------------- >> */
        setUpViewPager();



        initImageLoader();
    }

    private void setUpBottomNavigation() {
        /* << -------------------- enables navigation to other activities via BottomNavigation icons -------------------- >> */
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(HomeActivity.this, bottomNavigationViewEx);



        /* << -------------------- highlight selected menu item -------------------- >> */
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem item = menu.getItem(ACTIVITY_NUM);
        item.setChecked(true);
    }

    /* << -------------------- adds 2 tabs: Logo and Messages -------------------- >> */
    private void setUpViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new LogoFragment());
        adapter.addFragment(new MessageFragment());

        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_logo);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_message);
    }

    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
}
