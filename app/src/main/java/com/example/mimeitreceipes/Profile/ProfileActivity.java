package com.example.mimeitreceipes.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.mimeitreceipes.BotttomNav.BottomNavigationViewHelper;
import com.example.mimeitreceipes.Image.UniversalImageLoader;
import com.example.mimeitreceipes.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
    private ImageButton editProfileImageButton;
    private ImageView profileImageView;
    private ProgressBar profileInfoProgressBar;
    private RecyclerView.Adapter adapter;
    private static final int IMAGE_RATIO = 3;
    List<Post> imageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Log.d(TAG, "onCreate:started");



        profileImageView = findViewById(R.id.profileImageView);
        profileInfoProgressBar = findViewById(R.id.profileInforProgressBar);



        /* << -------------------- set up BottomNavigation -------------------- >> */
        setUpBottomNavigation();



        /* << -------------------- set up Toolbar with options menu -------------------- >> */
        setUpToolbar();



        setProfileImage();



        //TODO: Create activity that contains EditProfileFragment (can't use Intent to navigate to fragment)
        /* << -------------------- editProfileButton onClick() -------------------- >> */
        editProfileImageButton = findViewById(R.id.editProfileImageButton);
        //editProfileImageButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent toEditProfileActivity = new Intent(ProfileActivity.this, EditProfileFragment.class);
        //        startActivity(toEditProfileActivity);
        //    }
        //});



        /* << -------------------- TEMPORARY RECYCLER_VIEW SETUP -------------------- >> */
        temporaryRecyclerViewSetup();
        setUpRecyclerViewAdapter();
    }

    private void setUpBottomNavigation() {
        /* << -------------------- enables navigation to other activities via BottomNavigation icons -------------------- >> */
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(ProfileActivity.this, bottomNavigationViewEx);



        /* << -------------------- highlight selected menu item -------------------- >> */
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem item = menu.getItem(ACTIVITY_NUM);
        item.setChecked(true);
    }

    //TODO: Finish options menu.
    private void setUpToolbar() {
        Toolbar profileToolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(profileToolbar);

        profileToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.d(TAG, "onMenuItemClick:clicked menu item " + menuItem);

                switch (menuItem.getItemId()) {
                    case R.id.signOut:
                        Log.d(TAG, "onMenuItemClick:Navigating to 'Sign Out'");
                        break;
                }
                return false;
            }
        });
    }

    /* << -------------------- necessary for options menu to show up on toolbar -------------------- >> */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    private void setProfileImage() {
        Log.d(TAG, "setProfileImage:started");
        String imageUrl = "https://storage.googleapis.com/gweb-uniblog-publish-prod/images/android_ambassador_v1_cmyk_200px.max-200x200.png";
        UniversalImageLoader.setImage(imageUrl, profileImageView, profileInfoProgressBar, "");
    }

    private void temporaryRecyclerViewSetup() {
        imageUrls = new ArrayList<>();
            imageUrls.add(new Post("https://dkl2gez3gijkp.cloudfront.net/lib/uploads/2017/06/How-To-Make-A-Cool-Animated-H" +
                    "amburger-Mobile-Menu-Icon.jpg", "", "Five Guys", "Hamburger", "This is a hamburger.", null));
            imageUrls.add(new Post("https://media.istockphoto.com/vectors/matzo-ball-soup-flat-design-hanukkah-icon-vector-id968546288?k=6&m=968546288" +
                    "&s=612x612&w=0&h=JDWfy9aePJA41gCMmFD9otFTEhFVoNY8lAvD2PLcY0s=", "", "Olive Garden", "Soup", "This is a creamy soup.", null));
            imageUrls.add(new Post("https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/uh59Wh0/sandwich-cartoon-illustration-hand-" +
                    "drawn-animation-transparent_v1rd85s__F0004.png", "", "Subway", "Foot long", "This is a foot long sandwich.", null));
            imageUrls.add(new Post("http://pizzapresser.com/img/newpizza.png",
                    "", "Mellow Mushroom", "Pizza", "This is a pizza.", null));
            imageUrls.add(new Post("https://media.istockphoto.com/vectors/vector-illustration-fresh-tasty-hot-pancakes-with-sweet-maple-syrup" +
                    "-vector-id927455476?k=6&m=927455476&s=612x612&w=0&h=JG7reOciDgE" +
                    "Q0fljk5snrHJwI0X7auHXTNWilye718U=", "", "i-Hop", "Pancakes", "These are fluffy pancakes.", null));
    }

    private void setUpRecyclerViewAdapter() {
        RecyclerView recyclerView = findViewById(R.id.profileRecyclerView);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProfileRecyclerViewAdapter(ProfileActivity.this, imageUrls);
        recyclerView.setAdapter(adapter);
    }
}
