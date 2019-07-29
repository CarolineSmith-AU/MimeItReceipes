package com.example.mimeitreceipes.Share;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mimeitreceipes.BotttomNav.BottomNavigationViewHelper;
import com.example.mimeitreceipes.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ShareActivity extends AppCompatActivity {

    private static final String TAG = "ShareActivity";
    private static final int ACTIVITY_NUM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Log.d(TAG, "onCreate:started");

        /* << -------------------- set up BottomNavigation -------------------- >> */
        setUpBottomNavigation();
    }

    private void setUpBottomNavigation() {
        /* << -------------------- enables navigation to other activities via BottomNavigation icons -------------------- >> */
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(ShareActivity.this, bottomNavigationViewEx);



        /* << -------------------- highlight selected menu item -------------------- >> */
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem item = menu.getItem(ACTIVITY_NUM);
        item.setChecked(true);
    }
}
