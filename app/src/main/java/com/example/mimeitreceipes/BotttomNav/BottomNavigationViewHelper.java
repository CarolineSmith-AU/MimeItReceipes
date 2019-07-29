package com.example.mimeitreceipes.BotttomNav;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.example.mimeitreceipes.Share.ShareActivity;
import com.example.mimeitreceipes.Home.HomeActivity;
import com.example.mimeitreceipes.Popular.StarActivity;
import com.example.mimeitreceipes.Profile.ProfileActivity;
import com.example.mimeitreceipes.R;
import com.example.mimeitreceipes.Search.SearchActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavViewHelper";

    public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
        Log.d(TAG, "enableNavigation:started");
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.ic_home: //ACTIVITY_NUM = 0
                        Log.d(TAG, "case R.id.ic_home");
                        Intent toHomeActivity = new Intent(context, HomeActivity.class);
                        context.startActivity(toHomeActivity);
                        break;

                    case R.id.ic_add: //ACTIVITY_NUM = 1
                        Log.d(TAG, "case R.id.ic_add");
                        Intent toShareActivity = new Intent(context, ShareActivity.class);
                        context.startActivity(toShareActivity);
                        break;

                    case R.id.ic_search: //ACTIVITY_NUM = 2
                        Log.d(TAG, "case R.id.ic_search");
                        Intent toSearchActivity = new Intent(context, SearchActivity.class);
                        context.startActivity(toSearchActivity);
                        break;

                    case R.id.ic_star: //ACTIVITY_NUM = 3
                        Log.d(TAG, "case R.id.ic_star");
                        Intent toStarActivity = new Intent(context, StarActivity.class);
                        context.startActivity(toStarActivity);
                        break;

                    case R.id.ic_profile: //ACTIVITY_NUM = 4
                        Log.d(TAG, "case R.id.ic_profile");
                        Intent toProfileActivity = new Intent(context, ProfileActivity.class);
                        context.startActivity(toProfileActivity);
                        break;
                }

                return false;
            }
        });
    }
}
