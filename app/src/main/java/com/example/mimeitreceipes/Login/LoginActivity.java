package com.example.mimeitreceipes.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mimeitreceipes.Firebase.FirebaseMethods;
import com.example.mimeitreceipes.Home.HomeActivity;
import com.example.mimeitreceipes.R;
import com.example.mimeitreceipes.Register.RegisterActivity;

import com.example.mimeitreceipes.ValidEmailPass.Valid;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private Button loginButton;
    private TextView signUpTextView;
    private ProgressBar loginRequestProgressBar; //TODO: Figure out how to handle ProgressBar
    private FirebaseMethods firebaseMethods;
    private static final String TAG = "LoginActivity";
    private Valid valid;
    public String PREFS = "MyPrefs";
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG, "onCreate:started");



        /* << -------------------- initialize things -------------------- >> */
        init();



        /* << -------------------- set up toolbar -------------------- >> */
        setUpToolBar();



        /* << -------------------- make signUpTextView clickable -------------------- >> */
        String text = "Don't have an account? Sign up!";
        SpannableString ss = new SpannableString(text);
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent toSignUpActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(toSignUpActivity);
            }
        };
        ss.setSpan(span, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpTextView.setText(ss);
        signUpTextView.setMovementMethod(LinkMovementMethod.getInstance()); //necessary for TextView link to work



        /* << -------------------- call onStart() -------------------- >> */
        onStart();



        /* << -------------------- onClick() for loginButton -------------------- >> */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTextInputLayout.getEditText().getText().toString().trim();
                String password = passwordTextInputLayout.getEditText().getText().toString();

                valid = new Valid(LoginActivity.this, emailTextInputLayout, passwordTextInputLayout);

                if (valid.validForms()) {
                    firebaseMethods.signIn(email, password, HomeActivity.class);
                }
            }
        });
    }

    /* << -------------------- checks if user already signed in and update UI accordingly -------------------- >> */
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        firebaseMethods.updateUI(currentUser, LoginActivity.this, HomeActivity.class);
    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Login");
    }

    private void init() {
        /* << -------------------- set up SharedPreferences -------------------- >>*/
        sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        /* << -------------------- init widgets -------------------- >> */
        emailTextInputLayout = findViewById(R.id.emailTextInputLayout);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        loginButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.signUpTextView);
        loginRequestProgressBar = findViewById(R.id.loginRequestProgressBar);

        /* << -------------------- init Firebase auth. -------------------- >> */
        firebaseMethods = new FirebaseMethods(LoginActivity.this);
        mAuth = FirebaseAuth.getInstance();
    }
}
