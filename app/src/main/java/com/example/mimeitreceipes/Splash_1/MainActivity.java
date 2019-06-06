package com.example.mimeitreceipes.Splash_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mimeitreceipes.Login.LoginActivity;
import com.example.mimeitreceipes.R;

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
