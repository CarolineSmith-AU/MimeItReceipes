package com.example.mimeitreceipes.Firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.Toast;

import com.example.mimeitreceipes.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBaseMethods {
    private FirebaseAuth mAuth;
    private Context context;
    private SharedPreferences sharedPreferences;
    private static String PREFS = "Prefs";
    private String userID;
    private static final String TAG = "FireBaseMethods";
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;

    public FireBaseMethods(Context contextIn) {
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }
        context = contextIn;
        sharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    /**
     * Used in 'RegisterActivity'
     * @param email
     * @param password
     * @param username
     * @param targetClass
     */
    public void createAccount(String email, String password, final String username,
                              final TextInputLayout usernameTextInputLayout, final Class targetClass) {
        Log.d(TAG, "createAccount:started");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            userID = mAuth.getCurrentUser().getUid();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUIRegister(user, username, usernameTextInputLayout, context, targetClass);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUIRegister(null, username, usernameTextInputLayout, context, targetClass);
                        }
                    }
                });
    }

    /**
     * Used in 'LoginActivity' - Signs in user
     * @param email
     * @param password
     * @param targetClass
     */
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

    public void updateUIRegister(final FirebaseUser user, final String username, final TextInputLayout emailTextInputLayout,
                                 Context thisContext, Class targetClass) {
        Log.d(TAG, "updateUIRegister:started");

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        if (user != null) {
            //Get current user info.
            getCurrentUser();

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //1st: Check if username already in use
                    if (checkIfUsernameExists(username, dataSnapshot) == true) {
                        Log.d(TAG, "Username already exists");
                        emailTextInputLayout.setError("This username already exists.");
                        return;
                    }

                    //2nd: Add new users to database


                    //3rd: Add new user_account_settings to database

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            //Go to HomeActivity
            Intent toNextActivity = new Intent(thisContext, targetClass);
            thisContext.startActivity(toNextActivity);
        }
    }

    public boolean checkIfUsernameExists(String username, DataSnapshot dataSnapshot) {
        Log.d(TAG, "checkIfUsernameExists:checking if " + username  + " already exists");

        User user = new User();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Log.d(TAG, "checkIfUsernameExists:dataSnapshot: " + ds);

            user.setUsername(ds.getValue(User.class).getUsername());

            if (user.getUsername().equals(username)) {
                Log.d(TAG, "checkIfUsernameExists:FOUND MATCH");
                return true;
            }
        }
        return false;
    }

    public void addNewUser(String email, String username, String description, String profile_photo) {
        User user = new User(email, username, userID);
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
