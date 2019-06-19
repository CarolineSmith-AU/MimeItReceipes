package com.example.mimeitreceipes.Popular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mimeitreceipes.R;

public class PopularActivity extends AppCompatActivity {

    private static final String TAG = "PopularActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        Log.d(TAG, "onCreate:started");
    }
}
