package com.example.mimeitreceipes.ValidEmailPass;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.Toast;

public class Valid {
    private Context context;
    private String email;
    private String password;
    private TextInputLayout emailTextInputLayout, passwordTextInputLayout;
    private static final String TAG = "Valid";

    public Valid(Context contextIn, TextInputLayout emailTextInputLayoutIn, TextInputLayout passwordTextInputLayoutIn) {
        context = contextIn;
        emailTextInputLayout = emailTextInputLayoutIn;
        passwordTextInputLayout = passwordTextInputLayoutIn;
        email = emailTextInputLayout.getEditText().getText().toString().trim();
        password = passwordTextInputLayout.getEditText().getText().toString().trim();
    }

    public boolean validForms() {
        boolean valid = true;

        if (!validEmail() | !validPassword()) { //use '|' for ALL error messages to show
            Log.d(TAG, "validForms:false");
            Toast.makeText(context, "Double-check fields.", Toast.LENGTH_LONG).show();
            valid = false;
        }
        return valid;
    }

    public boolean validEmail() {
        boolean valid;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) { //if email field empty
            Log.d(TAG, "validEmail:false   EMPTY");
            emailTextInputLayout.setError("Field cannot be empty.");
            valid = false;
        }
        else if (!email.matches(emailPattern)) { //if email does not contain '@'
            Log.d(TAG, "validEmail:false   INVALID_PATTERN");
            emailTextInputLayout.setError("Please enter a valid email.");
            valid = false;
        }
        else {
            Log.d(TAG, "validEmail:true");
            emailTextInputLayout.setError(null);
            valid = true;
        }

        return valid;
    }

    public boolean validPassword() {
        boolean valid;

        if (password.isEmpty()) { //if password field empty
            Log.d(TAG, "validPassword:false   EMPTY");
            passwordTextInputLayout.setError("Field cannot be empty.");
            valid = false;
        }
        else if (password.length() < 8) {
            Log.d(TAG, "validPassword:false   INVALID_LENGTH");
            passwordTextInputLayout.setError("Password must be at least 8 characters long.");
            valid = false;
        }
        else {
            Log.d(TAG, "validPassword:true");
            passwordTextInputLayout.setError(null);
            valid = true;
        }
        return valid;
    }

}
