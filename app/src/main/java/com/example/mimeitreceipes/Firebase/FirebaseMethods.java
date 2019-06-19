package com.example.mimeitreceipes.Firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseMethods {
    private FirebaseAuth mAuth;
    private Context context;
    private SharedPreferences sharedPreferences;
    private static String PREFS = "Prefs";
    private static final String TAG = "FirebaseMethods";

    public FirebaseMethods(Context contextIn) {
        mAuth = FirebaseAuth.getInstance();
        context = contextIn;
        sharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public void createAccount(String email, String password, String username, final Class targetClass) {
        Log.d(TAG, "createAccount:started");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user, context, targetClass);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null, context, targetClass);
                        }

                        // ...

                    }
                });
    }

    /* << -------------------- signs in user -------------------- >> */
    public void signIn(String email, String password, final Class targetClass) {
        Log.d(TAG, "signIn:started");
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user, context, targetClass);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null, null, null);
                        }

                        // ...
                    }
                });
    }

    public void updateUI(FirebaseUser user, Context thisContext, Class targetClass) {
        Log.d(TAG, "updateUI:started");
        if (user != null) {
            //Get current user info.
            getCurrentUser();

            //Go to HomeActivity
            Intent toNextActivity = new Intent(thisContext, targetClass);
            thisContext.startActivity(toNextActivity);
        }
    }

    public void getCurrentUser() {
        Log.d(TAG, "getCurrentUser:started");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (user != null) {
            String email = user.getEmail();
            Log.d(TAG, "getCurrentUser(email):" + email);
            editor.putString("email", email);

            //Check is user's email is verified
            boolean emailVerified = user.isEmailVerified();

            String uid = user.getUid();
            Log.d(TAG, "getCurrentUser(UID)" + uid);
            editor.putString("uid", uid);

            editor.apply();
        }
    }

    public void sendEmailVerification() {
        Log.d(TAG, "sendEmailVerification:started");
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "sendEmailVerification:success");
                                Toast.makeText(context,
                                        "Verification email sent to " + user.getEmail(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e(TAG, "sendEmailVerification:failed");
                                Toast.makeText(context,
                                        "Error sending verification email.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void signOut() {
        Log.d(TAG, "signOut:started");
        mAuth.signOut();
        updateUI(null, null, null);
    }
}
