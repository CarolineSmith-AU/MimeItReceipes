package com.example.mimeitreceipes.Register;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mimeitreceipes.Firebase.FirebaseMethods;
import com.example.mimeitreceipes.Home.HomeActivity;
import com.example.mimeitreceipes.R;
import com.example.mimeitreceipes.ValidEmailPass.Valid;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button registerButton;
    private TextInputLayout usernameTextInputLayout;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private FirebaseMethods firebaseMethods;
    private Valid valid;
    private static final String TAG = "RegisterActivity";
    public static String PREFS = "MyPrefs";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.d(TAG, "onCreate:started");



        /* << -------------------- initialize things -------------------- >> */
        initWidgets();



        /* << -------------------- set up toolbar -------------------- >> */
        setUpToolBar();



        /* << -------------------- Initialize Firebase Auth -------------------- >> */
        firebaseMethods = new FirebaseMethods(RegisterActivity.this);



        /* << -------------------- onClick() for registerButton -------------------- >> */
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameTextInputLayout.getEditText().getText().toString().trim();
                String email = emailTextInputLayout.getEditText().getText().toString().trim();
                String password = passwordTextInputLayout.getEditText().getText().toString().trim();

                valid = new Valid(RegisterActivity.this, emailTextInputLayout, passwordTextInputLayout);

                if (valid.validForms()) {
                    firebaseMethods.createAccount(email, password, username, HomeActivity.class);
                }
            }
        });
    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("SignUp");
    }

    private void initWidgets() {
        /* << -------------------- init widgets -------------------- >> */
        registerButton = findViewById(R.id.registerButton);
        usernameTextInputLayout = findViewById(R.id.usernameTextInputLayout);
        emailTextInputLayout = findViewById(R.id.emailTextInputLayout);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);

        /* << -------------------- set up SharedPreferences -------------------- >> */
        sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}
