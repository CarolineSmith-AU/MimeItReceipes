package com.example.mimeitreceipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int SLEEP_TIMER = 4000;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent toLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(toLoginActivity);
                finish();
            }
        }, SLEEP_TIMER);
    }
}
